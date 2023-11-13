package com.sist.main;
import java.util.Scanner;

import com.sist.dao.*;


public class EmpMain {
	public static void main(String[] args) {
		EmpDAO dao=new EmpDAO();
		//dao.empListData();
		//dao.empNotCommListData();
		//dao.empCommListData();
		//dao.empFindData();
		/*
		Scanner scan=new Scanner(System.in);
		System.out.println("검색어 입력: ");
		String ename=scan.next();
		dao.empFindData(ename.toUpperCase());
		*/
		//dao.empRpadData();
		//dao.subqueryNotData();
		dao.subqueryData();
	}
}
