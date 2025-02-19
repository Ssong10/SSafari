package com.ssafy.edu.vue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.vue.dto.Commentportfolio;
import com.ssafy.edu.vue.dto.Member;
import com.ssafy.edu.vue.dto.Message;
import com.ssafy.edu.vue.dto.Portfolio;
import com.ssafy.edu.vue.dto.Sugang;
import com.ssafy.edu.vue.help.BoolResult;
import com.ssafy.edu.vue.service.IMemberService;
import com.ssafy.edu.vue.service.IMessageService;
import com.ssafy.edu.vue.service.IPortfolioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "A5 Resouces Management 2020")
public class PortfolioController {

	public static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

	@Autowired
	private IPortfolioService portfolioservice;
	@Autowired
	private IMessageService messageservice;
	@Autowired
	private IMemberService memberservice;
	
	@ApiOperation(value = "portfolio 전체 보기", response = List.class)
	@RequestMapping(value = "/portfolios", method = RequestMethod.GET)
	public ResponseEntity<List<Portfolio>> getPortfolios() throws Exception {
		logger.info("1-------------getPortfolios-----------------------------" + new Date());
		List<Portfolio> portfolios = portfolioservice.getPortfolios();
		
		return new ResponseEntity<List<Portfolio>>(portfolios, HttpStatus.OK);
	}
	
	@ApiOperation(value = "portfolio 멤버별 전체 보기", response = List.class)
	@RequestMapping(value = "/portfoliolist/{memberid}", method = RequestMethod.GET)
	public ResponseEntity<List<Portfolio>> getPortfolioList(@PathVariable int memberid) throws Exception {
		logger.info("1-------------getPortfolioList-----------------------------" + new Date());
		List<Portfolio> portfolios = portfolioservice.getPortfolioList(memberid);
		
		return new ResponseEntity<List<Portfolio>>(portfolios, HttpStatus.OK);
	}
	
	@ApiOperation(value = "portfolio 상세 보기", response = List.class)
	@RequestMapping(value = "/portfolio/{portfolioid}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getPortfolio(@PathVariable int portfolioid) throws Exception {
		logger.info("1-------------getPortfolio-----------------------------" + new Date());
		Map<String,Object> result = new HashMap();
		
		Portfolio portfolio = portfolioservice.getPortfolio(portfolioid);
		result.put("portfolio", portfolio);
		
		List<Sugang> sugang = portfolioservice.getSugangMember(portfolioid);
		result.put("sugang", sugang);
		
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "portfolio 추가", response = List.class)
	@RequestMapping(value = "/portfolio", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> addPortfolio(@RequestBody Portfolio portfolio) throws Exception {
		logger.info("1-------------addPortfolio-----------------------------" + new Date());
		portfolioservice.addPortfolio(portfolio);
		BoolResult nr=new BoolResult();
   		nr.setName("addPortfolio");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "portfolio 수정", response = BoolResult.class)
	@RequestMapping(value = "/portfolio", method = RequestMethod.PUT)
	public ResponseEntity<BoolResult> updatePortfolio(@RequestBody Portfolio portfolio) throws Exception {
		logger.info("1-------------updatePortfolio-----------------------------" + new Date());
		Portfolio port = portfolioservice.getPortfolio(portfolio.getPortfolioid());
		logger.info("2----updatePortfolio---" + portfolio);
		if(portfolio.getTitle()==null || portfolio.getTitle().equals("")) {
			portfolio.setTitle(port.getTitle());
		}
		if(portfolio.getBody()==null || portfolio.getBody().equals("")) {
			portfolio.setBody(port.getBody());
		}
		if(portfolio.getImg()==null || portfolio.getImg().equals("")) {
			portfolio.setImg(port.getImg());
		}
		if(portfolio.getCapacity()==0) {
			portfolio.setCapacity(port.getCapacity());
		}
		if(portfolio.getStartdate()==null || portfolio.getStartdate().equals("")) {
			portfolio.setStartdate(port.getStartdate());
		}
		if(portfolio.getEnddate()==null || portfolio.getEnddate().equals("")) {
			portfolio.setEnddate(port.getEnddate());
		}
		if(portfolio.getPrice()==0) {
			portfolio.setPrice(port.getPrice());
		}
		if(portfolio.getLocationid()==0) {
			portfolio.setLocationid(port.getLocationid());
		}
		logger.info("3----updatePortfolio---" + portfolio);
		portfolioservice.updatePortfolio(portfolio);
		BoolResult nr=new BoolResult();
   		nr.setName("updatePortfolio");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "portfolio 삭제", response = BoolResult.class)
	@RequestMapping(value = "/portfolio/{portfolioid}", method = RequestMethod.DELETE)
	public ResponseEntity<BoolResult> deletePortfolio(@PathVariable int portfolioid) throws Exception {
		logger.info("1-------------deletePortfolio-----------------------------" + new Date());
		portfolioservice.deletePortfolio(portfolioid);
		BoolResult nr=new BoolResult();
   		nr.setName("deletePortfolio");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "portfolio Comment 전체 보기", response = List.class)
	@RequestMapping(value = "/commentportfolio/{portfolioid}", method = RequestMethod.GET)
	public ResponseEntity<List<Commentportfolio>> getCommentPortfolio(@PathVariable int portfolioid) throws Exception {
		logger.info("1-------------getCommentPortfolio-----------------------------" + new Date());
		List<Commentportfolio> portfolios = portfolioservice.getCommentPortfolio(portfolioid);
		
		return new ResponseEntity<List<Commentportfolio>>(portfolios, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = { "*" }, maxAge = 6000)
	@ApiOperation(value = "portfolio Comment 추가", response = List.class)
	@RequestMapping(value = "/commentportfolio", method = RequestMethod.POST)
	public ResponseEntity<List<Commentportfolio>> addCommentPortfolio(@RequestBody Commentportfolio commentportfolio) throws Exception {
		logger.info("1-------------addCommentPortfolio-----------------------------" + new Date());
		portfolioservice.addCommentPortfolio(commentportfolio);
		List<Commentportfolio> portfolios = portfolioservice.getCommentPortfolio(commentportfolio.getPortfolioid());
		Portfolio portfolio = portfolioservice.getPortfolio(commentportfolio.getPortfolioid());
		messageservice.addMessage(new Message(1,portfolio.getMemberid(),"댓글 알림", "'"+portfolio.getTitle()+"' 스터디 모집글에 댓글이 작성되었습니다."));
		return new ResponseEntity<List<Commentportfolio>>(portfolios, HttpStatus.OK);
	}
	
	@ApiOperation(value = "portfolio Comment 수정", response = BoolResult.class)
	@RequestMapping(value = "/commentportfolio", method = RequestMethod.PUT)
	public ResponseEntity<List<Commentportfolio>> updateCommentPortfolio(@RequestBody Commentportfolio commentportfolio) throws Exception {
		logger.info("1-------------updateCommentPortfolio-----------------------------" + new Date());
		portfolioservice.updateCommentPortfolio(commentportfolio);
		List<Commentportfolio> portfolios = portfolioservice.getCommentPortfolio(commentportfolio.getPortfolioid());
		return new ResponseEntity<List<Commentportfolio>>(portfolios, HttpStatus.OK);
	}
	
	@ApiOperation(value = "portfolio Comment 삭제", response = BoolResult.class)
	@RequestMapping(value = "/commentportfolio", method = RequestMethod.DELETE)
	public ResponseEntity<List<Commentportfolio>> deleteCommentPortfolio(@RequestBody Commentportfolio commentportfolio) throws Exception {
		logger.info("1-------------deleteCommentPortfolio-----------------------------" + new Date());
		portfolioservice.deleteCommentPortfolio(commentportfolio.getCportfolioid());
		List<Commentportfolio> portfolios = portfolioservice.getCommentPortfolio(commentportfolio.getPortfolioid());
		return new ResponseEntity<List<Commentportfolio>>(portfolios, HttpStatus.OK);
	}
	
	@ApiOperation(value = "강좌 수강 신청", response = List.class)
	@RequestMapping(value = "/sugang", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> registerSugang(@RequestBody Sugang sugang) throws Exception {
		logger.info("1-------------registerSugang-----------------------------" + new Date());
		portfolioservice.registerSugang(sugang);
		Portfolio portfolio = portfolioservice.getPortfolio(sugang.getPortfolioid());
		Member member = memberservice.getMember(sugang.getMemberid());
		messageservice.addMessage(new Message(1,portfolio.getMemberid(),"스터디 신청", "'"+member.getUsername()+"'님이 '"+portfolio.getTitle()+"' 스터디를 신청하였습니다."));
		BoolResult nr=new BoolResult();
   		nr.setName("registerSugang");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	@ApiOperation(value = "강좌 수강 취소", response = BoolResult.class)
	@RequestMapping(value = "/sugang", method = RequestMethod.DELETE)
	public ResponseEntity<BoolResult> deletePortfolio(@RequestBody Sugang sugang) throws Exception {
		logger.info("1-------------deleteSugang-----------------------------" + new Date());
		portfolioservice.deleteSugang(sugang);
		BoolResult nr=new BoolResult();
   		nr.setName("deleteSugang");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "강좌 수강 여부 확인", response = List.class)
	@RequestMapping(value = "/sugang", method = RequestMethod.GET)
	public ResponseEntity<Integer> isApplicant(@ModelAttribute Sugang sugang) throws Exception {
		logger.info("1-------------isApplicant-----------------------------" + new Date());
		int result = portfolioservice.isApplicant(sugang);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "portfolio 수강생 보기", response = List.class)
	@RequestMapping(value = "/sugang/{portfolioid}", method = RequestMethod.GET)
	public ResponseEntity<List<Sugang>> getSugangMember(@PathVariable int portfolioid) throws Exception {
		logger.info("1-------------getSugangMember-----------------------------" + new Date());
		List<Sugang> sugang = portfolioservice.getSugangMember(portfolioid);
		
		return new ResponseEntity<List<Sugang>>(sugang, HttpStatus.OK);
	}
	
	@ApiOperation(value = "수강 중인 강좌 목록 보기", response = List.class)
	@RequestMapping(value = "/memberportfolio/{memberid}", method = RequestMethod.GET)
	public ResponseEntity<List<Portfolio>> getMemberPortfolio(@PathVariable int memberid) throws Exception {
		logger.info("1-------------getMemberPortfolio-----------------------------" + new Date());
		List<Portfolio> portfolios = portfolioservice.getMemberPortfolio(memberid);
		
		return new ResponseEntity<List<Portfolio>>(portfolios, HttpStatus.OK);
	}

}
