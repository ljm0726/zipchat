package com.ssafy.news.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.ssafy.apt.service.DealInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@Controller
@RequestMapping("/news")
@Api("아파트 컨트롤러  API V1")
public class NewsController {

	private final Logger logger = LoggerFactory.getLogger(NewsController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String clientId = "id";
	private static final String clientSecret = "secret"; 
	private static final String apiUrl = "https://openapi.naver.com/v1/search/news?display=3&query=";

	@Autowired
	public NewsController(DealInfoService dealInfoservice) {
		super();
		logger.info("NewsController 호출");
	}

	@ApiOperation(value = "검색 결과", notes = "검색 결과를 반환한다.", response = List.class)
	@GetMapping("/{search}")
	public ResponseEntity<String> newsSearch(
			@PathVariable("search") @ApiParam(value = "검색어", required = true) String search) throws Exception {
		search = URLEncoder.encode(search, "UTF-8"); //url utf-8로 인코딩
		String searchUrl = apiUrl + search;
		
		Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(searchUrl,requestHeaders);

		
		return new ResponseEntity<String>(responseBody, HttpStatus.OK);
	}

	private String get(String apiUrl, Map<String, String> requestHeaders){ // Get 호출
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
	
	 private HttpURLConnection connect(String apiUrl){ // HTTP 연결
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
    }
	 
	 private String readBody(InputStream body){ // 응답 결과 가공
	        InputStreamReader streamReader = new InputStreamReader(body);

	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();
	            
	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }

	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }
}
