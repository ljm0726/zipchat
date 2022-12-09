package com.ssafy.apt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ssafy.apt.model.dto.Bus;
import com.ssafy.apt.model.dto.DealInfo;
import com.ssafy.apt.model.dto.School;
import com.ssafy.apt.model.dto.SidoGugunDong;
import com.ssafy.apt.service.DealInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@Controller
@RequestMapping("/apt")
@Api("아파트 컨트롤러  API V1")
public class DealInfoController {

	private final Logger logger = LoggerFactory.getLogger(DealInfoController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private DealInfoService dealInfoservice;

	@Autowired
	public DealInfoController(DealInfoService dealInfoservice) {
		super();
		logger.info("DealInfoController 호출");
		this.dealInfoservice = dealInfoservice;
	}

	@GetMapping("my-favorite")
	public String mvFavorite() {
		return "apt/favorite";
	}

	@ApiOperation(value = "버스정류장 정보", notes = "가까운 버스정류장을 반환한다.", response = List.class)
	@GetMapping("near-bus/{lat}/{lng}")
	@ResponseBody
	public ResponseEntity<String> getNearBus(@PathVariable("lat") String lat,
			@PathVariable("lng") String lng) throws ServletException, IOException, Exception {
			
			Map<String, String> map = new HashMap<>();
			map.put("lat", lat);
			map.put("lng", lng);
			
			List<Bus> busList = new ArrayList<>();
			busList = dealInfoservice.getNearBusInfo(map);
			
			String json = new Gson().toJson(busList);
			
			return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunDong>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunDong>>(dealInfoservice.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun/{sido}")
	public ResponseEntity<List<SidoGugunDong>> gugun(
			@PathVariable("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunDong>>(dealInfoservice.getGugun(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동 정보", notes = "전국의 동을 반환한다.", response = List.class)
	@GetMapping("/dong/{gugun}")
	public ResponseEntity<List<SidoGugunDong>> dong(@PathVariable("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<SidoGugunDong>>(dealInfoservice.getDong(gugun), HttpStatus.OK);
	}

	@ApiOperation(value = "관심 지역 목록", notes = "관심 지역 목록을 반환한다.", response = List.class)
	@GetMapping("/myFavorite/{id}")
	@ResponseBody
	public ResponseEntity<String> getMyFavorite(@PathVariable("id") @ApiParam(value = "관심지역 동정보.", required = true) String id) throws ServletException, IOException, SQLException {
			List<DealInfo> list = dealInfoservice.getMyFavorite(id);

			String json = new Gson().toJson(list);

			return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심 지역 아파트 목록", notes = "관심 지역 기준으로 아파트 목록을 반환한다.", response = List.class)
	@GetMapping("/my-list/{dongCode}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> doMyList(@PathVariable("dongCode") @ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) String dongCode)
			throws ServletException, IOException, SQLException {
			logger.info("관심 지역 아파트 목록" + dongCode);
			
			List<DealInfo> list = dealInfoservice.getMyFavoriteList(dongCode);

			Map<String, Integer> map = new HashMap<>();
			for (int i = 0; i < 8; i++) {
				map.put(2015+i+"", 0);
			}
			for (DealInfo d:list) {
				map.put(d.getDealYear(), map.getOrDefault(d.getDealYear(), 0)+1);
			}
			
			Map<String, Integer> sortedMap = new TreeMap<>(map);
			List<Integer> list2 = new ArrayList<Integer>();
			for (String year: sortedMap.keySet()){
				list2.add(sortedMap.get(year));
			}

			Map<String, Object> map2 = new HashMap<>();

			map2.put("dealInfoList", list);
			map2.put("dealCount", list2);

			return new ResponseEntity<Map<String, Object>>(map2, HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심 지역 메인 아파트 목록", notes = "관심 지역 기준으로 아파트 목록을 반환한다.", response = List.class)
	@GetMapping("/mainFavoriteList/{dongCode}")
	@ResponseBody
	public ResponseEntity<String> getMainFavoriteList(@PathVariable("dongCode") @ApiParam(value = "관심 지역 리스트 아이디.", required = true) String dongCode)
			throws ServletException, IOException, SQLException {
			List<DealInfo> list = dealInfoservice.getMainFavoriteList(dongCode);
			logger.info("관심 지역 Main 아파트 목록 {}", dongCode);

			String json = new Gson().toJson(list);

			return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	

	@ApiOperation(value = "관심지역 등록", notes = "관심지역 목록을 반환한다.", response = Map.class)
	@PostMapping("/add-my-dong/{id}/{dongcode}")
	public ResponseEntity<String> doAddMyDong(@PathVariable("dongcode") @ApiParam(value = "관심지역 동정보.", required = true) String dongCode,
			@PathVariable("id") @ApiParam(value = "유저 id", required = true) String id)
			throws ServletException, IOException {

		try {
			dealInfoservice.like(id, dongCode);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "관심지역 해제", notes = "관심지역을 해제 한다.", response = String.class)
	@DeleteMapping("/del-my-dong/{id}/{dongcode}")
	public ResponseEntity<String> doDelMyDong(@PathVariable("dongcode") @ApiParam(value = "관심지역 동정보.", required = true) String dongCode,
			@PathVariable("id") @ApiParam(value = "유저 id", required = true) String id)
			throws ServletException, IOException {
		try {
			dealInfoservice.delMyDong(id, dongCode);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "관심 지역 목록", notes = "관심 지역 목록을 반환한다.", response = List.class)
	@GetMapping("/isFavorite/{id}/{dongcode}")
	@ResponseBody
	public ResponseEntity<String> isFavorite(@PathVariable("dongcode") @ApiParam(value = "관심지역 동정보.", required = true) String dongCode,
			@PathVariable("id") @ApiParam(value = "유저 id", required = true) String id) throws ServletException, IOException, SQLException {
		
		if(dealInfoservice.isFavoriteChk(id, dongCode) != null) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 목록", notes = "지역코드와 매매계약월을 기준으로 아파트 목록을 반환한다.", response = List.class)
	@GetMapping("/apt-list/{regCode}/{year}/{month}")
	@ResponseBody
	public ResponseEntity<String> doAptList(@PathVariable("regCode") String regCode, @PathVariable("year") String year,
			@PathVariable("month") String month)
			throws ServletException, IOException, SQLException {

		Map<String, String> map = new HashMap<>();

		map.put("regCode", regCode);
		map.put("year", year);
		map.put("month", month);
		List<DealInfo> list = dealInfoservice.getDealInfos(map);
		String json = new Gson().toJson(list);

		return new ResponseEntity<String>(json, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "현재 위도경도와 가까운 아파트 목록(거래내역 중복 제거).", response = List.class)
	@GetMapping("near-apt-one-by-latlng/{lat}/{lng}")
	@ResponseBody
	public ResponseEntity<String> getNearApartInfoOne(@PathVariable("lat") String lat,
			@PathVariable("lng") String lng) throws ServletException, IOException, Exception {
			Map<String, String> map = new HashMap<>();
			map.put("lat", lat);
			map.put("lng", lng);
			
			List<DealInfo> aptList = new ArrayList<>();
			aptList = dealInfoservice.getNearApartInfoOne(map);
			
			String json = new Gson().toJson(aptList);

			return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "마커를 누른 아파트의 전체 실거래 내역.", response = List.class)
	@GetMapping("near-apt-by-latlng/{lat}/{lng}/{apartmentName}")
	@ResponseBody
	public ResponseEntity<String> getNearApartInfo(@PathVariable("lat") String lat, @PathVariable("lng") String lng,
			@PathVariable("apartmentName") String apartmentName)throws ServletException, IOException, Exception {
			Map<String, String> map = new HashMap<>();
			map.put("lat", lat);
			map.put("lng", lng);
			map.put("apartmentName", apartmentName);
			
			List<DealInfo> aptPriceList = new ArrayList<>();
			
			aptPriceList = dealInfoservice.getNearApartInfo(map);

			String json = new Gson().toJson(aptPriceList);

			return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "학교 정보", notes = "가까운 학교를 반환한다.", response = List.class)
	@GetMapping("near-school/{lat}/{lng}")
	@ResponseBody
	public ResponseEntity<String> getNearSchool(@PathVariable("lat") String lat,
			@PathVariable("lng") String lng) throws ServletException, IOException, Exception {
			
			Map<String, String> map = new HashMap<>();
			map.put("lat", lat);
			map.put("lng", lng);
			
			List<School> schoolList = new ArrayList<>();
			schoolList = dealInfoservice.getNearSchoolInfo(map);
			
			String json = new Gson().toJson(schoolList);
			
			return new ResponseEntity<String>(json, HttpStatus.OK);
	}
}
