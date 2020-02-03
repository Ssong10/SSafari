package com.ssafy.edu.vue.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.vue.dto.Commentpost;
import com.ssafy.edu.vue.dto.Portfolio;
import com.ssafy.edu.vue.dto.Post;
import com.ssafy.edu.vue.help.BoolResult;
import com.ssafy.edu.vue.service.IPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "A5 Resouces Management 2020")
public class PostController {

	public static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private IPostService postservice;
	
	@ApiOperation(value = "post 전체 보기", response = List.class)
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getPosts() throws Exception {
		logger.info("1-------------getPortfolios-----------------------------" + new Date());
		List<Post> posts = postservice.getPosts();
		if (posts == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 멤버별 전체 보기", response = List.class)
	@RequestMapping(value = "/postlist/{memberid}", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getPostList(@PathVariable int memberid) throws Exception {
		logger.info("1-------------getPostList-----------------------------" + new Date());
		List<Post> posts = postservice.getPostList(memberid);
		if (posts == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 상세 보기", response = List.class)
	@RequestMapping(value = "/post/{postid}", method = RequestMethod.GET)
	public ResponseEntity<Post> getPost(@PathVariable int postid) throws Exception {
		logger.info("1-------------getPost-----------------------------" + new Date());
		Post post = postservice.getPost(postid);
		if (post == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 추가", response = List.class)
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> addPost(@RequestBody Post post) throws Exception {
		logger.info("1-------------addPost-----------------------------" + new Date());
		postservice.addPost(post);
		BoolResult nr=new BoolResult();
   		nr.setName("addPost");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 수정", response = BoolResult.class)
	@RequestMapping(value = "/post", method = RequestMethod.PUT)
	public ResponseEntity<BoolResult> updatePost(@RequestBody Post post) throws Exception {
		logger.info("1-------------updatePost-----------------------------" + new Date());
		postservice.updatePost(post);
		BoolResult nr=new BoolResult();
   		nr.setName("updatePost");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 삭제", response = BoolResult.class)
	@RequestMapping(value = "/post/{postid}", method = RequestMethod.DELETE)
	public ResponseEntity<BoolResult> deletePost(@PathVariable int postid) throws Exception {
		logger.info("1-------------deletePost-----------------------------" + new Date());
		postservice.deletePost(postid);
		BoolResult nr=new BoolResult();
   		nr.setName("deletePost");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post Comment 전체 보기", response = List.class)
	@RequestMapping(value = "/commentpost/{postid}", method = RequestMethod.GET)
	public ResponseEntity<List<Commentpost>> getCommentPost(@PathVariable int postid) throws Exception {
		logger.info("1-------------getCommentPost-----------------------------" + new Date());
		List<Commentpost> posts = postservice.getCommentPost(postid);
		if (posts == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Commentpost>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post Comment 추가", response = List.class)
	@RequestMapping(value = "/commentpost", method = RequestMethod.POST)
	public ResponseEntity<List<Commentpost>> addCommentPost(@RequestBody Commentpost commentpost) throws Exception {
		logger.info("1-------------addCommentPost-----------------------------" + new Date());
		postservice.addCommentPost(commentpost);
   		List<Commentpost> posts = postservice.getCommentPost(commentpost.getpostid());
		return new ResponseEntity<List<Commentpost>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post Comment 수정", response = BoolResult.class)
	@RequestMapping(value = "/commentpost", method = RequestMethod.PUT)
	public ResponseEntity<List<Commentpost>> updateCommentPost(@RequestBody Commentpost commentpost) throws Exception {
		logger.info("1-------------updateCommentPost-----------------------------" + new Date());
		postservice.updateCommentPost(commentpost);
		List<Commentpost> posts = postservice.getCommentPost(commentpost.getpostid());
		return new ResponseEntity<List<Commentpost>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post Comment 삭제", response = BoolResult.class)
	@RequestMapping(value = "/commentpost", method = RequestMethod.DELETE)
	public ResponseEntity<List<Commentpost>> deleteCommentPost(@RequestBody Commentpost commentpost) throws Exception {
		logger.info("1-------------deleteCommentPost-----------------------------" + new Date());
		postservice.deleteCommentPost(commentpost.getCpostid());
		List<Commentpost> posts = postservice.getCommentPost(commentpost.getpostid());
		return new ResponseEntity<List<Commentpost>>(posts, HttpStatus.OK);
	}
	
}
