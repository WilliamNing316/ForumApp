package com.example.ngm;

import java.util.ArrayList;

public class PublicData {
    static public ArrayList<MyItem> mItems = new ArrayList<>(); //posts records
    static public int post_order = 0;
    static public ArrayList<DraftItem> drafts = new ArrayList<>(); //drafts records
    static public ArrayList<CommentItem> commentItems = new ArrayList<>(); //drafts records
    static public int draft_order = 0;

    static public ArrayList<FanItem> fanItems = new ArrayList<>(); //fan records
    static public int fan_order = 0;
    static public ArrayList<FanItem> followingItems = new ArrayList<>(); //following records
    static public int following_order = 0;
    static public int height_screen = 100;
    static public int width_screen = 100;
    static public final String SERVER_URL = "http://183.173.114.57:8081";
    static public String account;
    static public User user = new User(null,"account","username");
    static public int currentActivity = -1;
}
