package com.example.own_music;

public class song {
    private String name;
    private String parth;
    private String singer;
    public song(String name,String parth,String singer)
    {
        this.name=name;
        this.parth=parth;
        this.singer=singer;
    }
    public String getName()
    {
        return name;
    }
    public String getParth()
    {
        return parth;
    }
    public String getsinger()
    {
        return singer;
    }

}
