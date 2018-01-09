package com.ddau.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddau.backend.pojo.Pet;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
/**
 * 
 */

/**
 * @author frere
 *
 */


@Api(value="演示API的使用",description="API的使用")
@Controller
public class ThisWillActuallyRun {

	@ApiOperation(notes = "index API", value = "首页API")
	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	@ApiOperation(notes = "pets API", value = "pets 列表")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/pets")
	@ResponseBody
	public List pagedPets() {
		List pets = new ArrayList<>();
		Pet pet = new Pet("Joie", 1, 12.3f);
		pets.add(pet);
		pet = new Pet("zerfs", 2, 3.3f);
		pets.add(pet);
		pet = new Pet("Elege", 3, 80.3f);
		pets.add(pet);
		return pets;
	}

}