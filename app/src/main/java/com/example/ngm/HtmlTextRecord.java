package com.example.ngm;

import java.util.ArrayList;

public class HtmlTextRecord {
    public String text_content;
    public ArrayList<textPiece> changed_text = new ArrayList<>();
    public class textPiece{
        public int start;
        public int end;
        public int font;
        public boolean if_bold;
        public boolean if_italic;
        public int color_type;
        public textPiece(int start, int end,int font, boolean if_bold, boolean if_italic, int color_type){
            this.start = start;
            this.end = end;
            this.font = font;
            this.if_bold = if_bold;
            this.if_italic = if_italic;
            this.color_type = color_type;
        }
    }
    public HtmlTextRecord(String text_content){
        this.text_content = text_content;
    }
    public void draw_html(){

    }
}
