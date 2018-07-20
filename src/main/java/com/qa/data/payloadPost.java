package com.qa.data;

public class payloadPost
{
    private String name;

    private String job;
    
    private String id;
    
	private String createdAt;
	
    public payloadPost(){
		
	}
	
	public payloadPost(String name,String job){
		this.name=name;
		this.job=job;		
	}
	
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getJob ()
    {
        return job;
    }

    public void setJob (String job)
    {
        this.job = job;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", job = "+job+"]";
    }
}