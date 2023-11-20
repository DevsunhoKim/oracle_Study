package com.sist.main;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.*;
public class MainClass {
	public static void main(String[] args) {
		MusicDAO dao=new MusicDAO();      
		try
		{
			Document doc=Jsoup.connect("https://www.genie.co.kr/chart/top200").get();
			Elements title=doc.select("table.list-wrap td.info a.title");
			Elements singer=doc.select("table.list-wrap td.info a.artist");
			Elements album=doc.select("table.list-wrap td.info a.albumtitle");
			Elements poster=doc.select("table.list-wrap a.cover img");
			
			for (int i=0;i<title.size();i++)
			{
				MusicVO vo=new MusicVO();
				vo.setTitle(title.get(i).text());
				vo.setSinger(singer.get(i).text());
				vo.setAlbum(album.get(i).text());
				vo.setPoster(poster.get(i).attr("src"));
				dao.musicInsert(vo);
			}
			System.out.println("Save End...");
		}catch(Exception ex)
		{}
	}
}
