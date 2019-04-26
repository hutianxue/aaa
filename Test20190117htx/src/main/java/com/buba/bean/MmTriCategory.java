package com.buba.bean;


public  class  MmTriCategory {
    private Integer id;

    private String name;

    private Integer parentId;

    private Integer nodeOrder;
    
    private boolean open;
    
    private String icon;

 
    public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean getOpen() {
		return true;
	}

	public void setOpen(boolean open) {
		this.open = open;
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

	@Override
	public String toString() {
		return "<MmTriCategory>\n"+
				"<id>"+id+"</id>\n"+
				"<name>"+name+"</name>\n"+
				"<parentId>"+parentId+"</parentId>\n"+
				"<nodeOrder>"+nodeOrder+"</nodeOrder>\n"+
				"</MmTriCategory>\n";
	}
}