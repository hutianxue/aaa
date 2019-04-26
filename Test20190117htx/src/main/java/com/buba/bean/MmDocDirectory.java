package com.buba.bean;

public class MmDocDirectory {
    private Integer id;

    private String name;

    private Integer parentId;

    private Integer nodeOrder;

    private String groupName;
    //图片
    private String icon;
    //树默认打开全部节点
    private boolean open;
    
    public boolean getOpen() {
		return true;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getNodeOrder() {
        return nodeOrder;
    }

    public void setNodeOrder(Integer nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

	@Override
	public String toString() {
		return "MmDocDirectory [id=" + id + ", name=" + name + ", parentId=" + parentId + ", nodeOrder=" + nodeOrder
				+ ", groupName=" + groupName + ", icon=" + icon + "]";
	}
}