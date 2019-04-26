package com.buba.webo.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.JedisCluster;

@Controller
public class UserController {
	@Autowired
	private JedisCluster jedisCluster;
	//注册
	@RequestMapping("/registe")
	public String registe(String username,String password,String password2,Model model,HttpSession session) {
		String userId = jedisCluster.get("user:username:"+username+":userid");
		if(userId==null) {
			long uid=jedisCluster.incrBy("uid", 1);
			jedisCluster.set("user:userid:"+uid+":username", username);
			jedisCluster.set("user:userid:"+uid+":password", password);
			jedisCluster.set("user:username:"+username+":userid", String.valueOf(uid));
			session.setAttribute("user", username);
			String userid = jedisCluster.get("user:username:"+username+":userid");
			session.setAttribute("uid", userid);
			return "home";
		}else {
			model.addAttribute("msg", "用户名已存在");
			return "index";
		}					
	}
	//登录
	@RequestMapping("/login")
	public String login(String username,String password,Model model,HttpSession session) {
		String uid = jedisCluster.get("user:username:"+username+":userid");
		String pwd = jedisCluster.get("user:userid:"+uid+":password");		
		if(pwd.equals(password)) {
			session.setAttribute("user", username);
			session.setAttribute("uid", uid);
			Long myFollowing = jedisCluster.scard("following:+myid:"+uid);
			Long follower = jedisCluster.scard("follower:+hisid:"+uid);
			model.addAttribute("follower", follower);
			model.addAttribute("mf", myFollowing);
			//获取登录的人的全部微博信息
			List<String> pidlist = jedisCluster.lrange("hotLine"+username, 0, -1);
			List<Map<String, String>> followlist=new ArrayList<Map<String, String>>();
			for (String own : pidlist) {
				Map<String, String> ownNews = jedisCluster.hgetAll("publishWb:pid:"+own);
				followlist.add(ownNews);
			}
			/*获取登录的人他所关注的人的全部微博信息*/
			//获取登录者关注的人的id
			Set<String> allMyfollow = jedisCluster.smembers("following:+myid:"+uid);
			//将登录者关注的人的名字存入集合
			List<String> allMyfollowNameList=new ArrayList<String>();
			List<Map<String, String>> allMyIdol=new ArrayList<Map<String, String>>();
			for (String allMyfollow1 : allMyfollow) {
				String allMyfollowName = jedisCluster.get("user:userid:"+allMyfollow1+":username");
				//获取登录者所关注的人的全部微博信息
				Map<String, String> allMyIdols = jedisCluster.hgetAll("publishWb:pid:"+allMyfollow1);
				allMyIdol.add(allMyIdols);
				allMyfollowNameList.add(allMyfollowName);
			}
			List<String> pidlistF = new ArrayList<String>();
			for (String allMyfollowNameList1 : allMyfollowNameList) {
				pidlistF=jedisCluster.lrange("hotLine"+allMyfollowNameList1, 0, -1);
			}
			
			for (String follow : pidlistF) {
				Map<String, String> followNews = jedisCluster.hgetAll("publishWb:pid:"+follow);
				followlist.add(followNews);
			}
			//让登陆者和他所关注的人的所有的微博 按照pid降序排列 定制排序
			Collections.sort(followlist, new Comparator<Map<String, String>>() {
	            public int compare(Map<String, String> o1, Map<String, String> o2) {
	                Integer name1 = Integer.valueOf(o1.get("pid")) ;//name1是从你list里面拿出来的一个 
	                Integer name2 = Integer.valueOf(o2.get("pid")) ; //name2是从你list里面拿出来的第二个name
	                return name2.compareTo(name1);
	            }
	        });
			
			model.addAttribute("webo", followlist);
			
			return "home";
		}
		else {
			model.addAttribute("msg2", "用户名或密码错误");
			return "index";
		}
	}
	//退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("uid");
		return "redirect:/jsps/index.jsp";
	}
	//主页 发布微博
	@RequestMapping("/publishWb")
	public String publishWb(String status,HttpSession session,Model model) {
		//将所需内容放入map集合
		long pid=jedisCluster.incrBy("pid", 1);
		//为了通过pid获取到所有发布的所有信息 存入list集合中 由左侧推入（其实就是倒序展示）
		jedisCluster.lpush("hotLine", String.valueOf(pid));		
		String userN = (String) session.getAttribute("user");
		String uid = (String) session.getAttribute("uid");
		//每个人单独的
		jedisCluster.lpush("hotLine"+userN, String.valueOf(pid));		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date da=new Date();
		String time = sf.format(da);
		Map<String,String> map=new HashMap<String,String>();
		map.put("pid", String.valueOf(pid));
		map.put("status", status);
		map.put("userId", uid);
		map.put("userNa", userN);
		map.put("time", time);
		//将所有发布信息存入hash集合中  在hash集合中 类似于存了一个对象 可以通过属性得到属性值
		jedisCluster.hmset("publishWb:pid:"+String.valueOf(pid), map);		
		//获取登录的人的全部微博信息
		List<Map<String, String>> followlist=new ArrayList<Map<String, String>>();
		List<String> pidlist = jedisCluster.lrange("hotLine"+userN, 0, -1);
		for (String own : pidlist) {
			Map<String, String> ownNews = jedisCluster.hgetAll("publishWb:pid:"+own);
			followlist.add(ownNews);
		}
		//获取登录的人他所关注的人的全部微博信息
		Set<String> allMyfollow = jedisCluster.smembers("following:+myid:"+uid);
		//获取登录的人关注的人的名字
		List<String> allMyfollowNameList=new ArrayList<String>();
		for (String allMyfollow1 : allMyfollow) {
			String allMyfollowName = jedisCluster.get("user:userid:"+allMyfollow1+":username");
			allMyfollowNameList.add(allMyfollowName);
		}
		//获取关注的所有的人的id
		List<String> pidlistF = new ArrayList<String>();
		for (String allMyfollowNameList1 : allMyfollowNameList) {
			pidlistF=jedisCluster.lrange("hotLine"+allMyfollowNameList1, 0, -1);
		}
		
		for (String follow : pidlistF) {
			Map<String, String> followNews = jedisCluster.hgetAll("publishWb:pid:"+follow);
			followlist.add(followNews);
		}
		//让登陆者和他所关注的人的所有的微博 按照pid降序排列
		Collections.sort(followlist, new Comparator<Map<String, String>>() {
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                Integer name1 = Integer.valueOf(o1.get("pid")) ;//name1是从你list里面拿出来的一个 
                Integer name2 = Integer.valueOf(o2.get("pid")) ; //name1是从你list里面拿出来的第二个name
                return name2.compareTo(name1);
            }
        });
		
		model.addAttribute("webo", followlist);
		Long myFollowing = jedisCluster.scard("following:+myid:"+uid);
		Long follower = jedisCluster.scard("follower:+hisid:"+uid);
		model.addAttribute("follower", follower);
		model.addAttribute("mf", myFollowing);
		return "home";
	}
	//热点 
	@RequestMapping("/hotNews")
	public String hotNews(Model model) {		
		//获取到所有pid的集合
		List<String> pidlist = jedisCluster.lrange("hotLine", 0, -1);
		List<Map<String, String>> hotNews=new ArrayList<Map<String, String>>();
		for (String allPid : pidlist) {
			//因为在遍历pid 可以通过hash的键得到其中的所有的属性及属性值 可以将其存入一个map集合
			Map<String, String> newsMap = jedisCluster.hgetAll("publishWb:pid:"+allPid);
			//再将这个map集合转存入一个list集合 就可以返回前端页面
			hotNews.add(newsMap);
		}		
		model.addAttribute("hotNews", hotNews);
		return "timeline";
	}
	//关注
	@RequestMapping("/guanzhu")
	public String guanZhu(String username,Model model,HttpSession session) {
		List<String> pidlist = jedisCluster.lrange("hotLine"+username, 0, -1);
		List<Map<String, String>> followlist=new ArrayList<Map<String, String>>();
		for (String own : pidlist) {
			Map<String, String> ownNews = jedisCluster.hgetAll("publishWb:pid:"+own);
			followlist.add(ownNews);
		}
		model.addAttribute("follow", followlist);
		model.addAttribute("uname", username);
		
		String uid = (String) session.getAttribute("uid");		
		String userid = jedisCluster.get("user:username:"+username+":userid");
		int i=1;//是否关注 1为还未关注 0为已关注 2为用户自己
		if(jedisCluster.sismember("following:+myid:"+uid, userid)) {
			i=0;
		}
		if(uid.equals(userid)) {
			i=2;
		}
		session.setAttribute("userid", userid);
		model.addAttribute("ifFollow", i);
		return "peofile";
	}
	@RequestMapping("/following")
	public String following(HttpSession session,Model model,String username) {
		String uid = (String) session.getAttribute("uid");
		String userid = (String) session.getAttribute("userid");
		//我关注的人
		jedisCluster.sadd("following:+myid:"+uid, userid);
		//被关注的人
		jedisCluster.sadd("follower:+hisid:"+userid, uid);		
		//查询被关注的那个的粉丝数量
		Long follower = jedisCluster.scard("follower:+hisid:"+userid);
		//查询我关注的人的数量
		Long myFollowing = jedisCluster.scard("following:+myid:"+uid);
		model.addAttribute("follower", follower);
		model.addAttribute("mf", myFollowing);
		return "forward:guanzhu";
	}
	@RequestMapping("/dontfollowing")
	public String dontfollowing(HttpSession session,Model model,String username) {
		//获取自己关注的这个人的id 
		String beiguanzhuzheId = jedisCluster.get("user:username:"+username+":userid");
		String uid = (String) session.getAttribute("uid");
		jedisCluster.srem("following:+myid:"+uid, beiguanzhuzheId);
		return "forward:guanzhu";
	}
	@ResponseBody
	@RequestMapping("/myIdol")
	public List<String> myIdol(HttpSession session){
		String uid = (String) session.getAttribute("uid");
		/*获取登录者所关注的人*/
		//idol的id
		Set<String> idolId = jedisCluster.smembers("following:+myid:"+uid);
		List<String> allMyIdol=new  ArrayList<String>();
		for (String idolsid : idolId) {
			String idols = jedisCluster.get("user:userid:"+idolsid+":username");
			allMyIdol.add(idols);
		}
		
		return allMyIdol;	
	}
	
	@ResponseBody
	@RequestMapping("/myfans")
	public List<String> myfans(HttpSession session){
		String uid = (String) session.getAttribute("uid");
		/*获取登录者的粉丝*/
		//fans的id
		Set<String> fansId = jedisCluster.smembers("follower:+hisid:"+uid);
		List<String> allMyFans=new  ArrayList<String>();
		for (String fansid : fansId) {
			String idols = jedisCluster.get("user:userid:"+fansid+":username");
			allMyFans.add(idols);
		}
		return allMyFans;	
	}
	
}
