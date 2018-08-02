package com.boot.spring.system.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.spring.system.service.SystemService;
import com.google.gson.Gson;

@Controller
public class SystemController {
	
	@Resource(name = "systemService")
	SystemService systemService;

	@RequestMapping(value="/showDisk")
	@ResponseBody
	public String showDisk() throws NoHeadException, GitAPIException {
		
		try {
			Repository repository = new FileRepositoryBuilder()
			    .setGitDir(new File("C:/Users/acer/Documents/workspace-sts-3.8.3.RELEASE/VueSpring/.git"))
			    .build();
			
			Config storedConfig = repository.getConfig();
            Set<String> remotes = storedConfig.getSubsections("remote");

            for (String remoteName : remotes) {
                String url = storedConfig.getString("remote", remoteName, "url");
                System.out.println(remoteName + " " + url);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map result = new HashMap<String, Object>();
		Gson gson = new Gson();
		
		return gson.toJson(systemService.showSystemInfo());
	}
	
	@RequestMapping(value="/getCommitLastDay")
	@ResponseBody
	public String getCommitLastDay() throws NoHeadException, GitAPIException {
		
		// Open an existing repository
		try {
			Repository existingRepo = new FileRepositoryBuilder()
			    .setGitDir(new File("C:/Users/acer/Documents/workspace-sts-3.8.3.RELEASE/VueSpring/.git"))
			    .build();
			
//			try (Repository repository = CookbookHelper.openJGitCookbookRepository()) {
	            try (Git git = new Git(existingRepo)) {
	                Iterable<RevCommit> commits = git.log().all().call();
	                int count = 0;
	                for (RevCommit commit : commits) {
	                    System.out.println("LogCommit: " + commit);
	                    count++;
	                }
	                System.out.println(count);
	            }
//	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
