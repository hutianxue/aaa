package com.buba.bean;

import java.io.Serializable;

public class Company  implements Serializable{
	 private static final long serialVersionUID = 1L;
	    private Integer id;
	    private String name;
	    private String address;

	    public Company() {}
	    public Company(String name, String address) {
	        this.name = name;
	        this.address = address;
	    }
	    public Company(Integer id, String name, String address) {
	        this.id = id;
	        this.name = name;
	        this.address = address;
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
	        this.name = name;
	    }
	    public String getAddress() {
	        return address;
	    }
	    public void setAddress(String address) {
	        this.address = address;
	    }
	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((id == null) ? 0 : id.hashCode());
	        return result;
	    }
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Company other = (Company) obj;
	        if (id == null) {
	            if (other.id != null)
	                return false;
	        } else if (!id.equals(other.id))
	            return false;
	        return true;
	    }
	    @Override
	    public String toString() {
	        return "Company [id=" + id + ", name=" + name + ", address=" + address + "]\n";
	    }

}
