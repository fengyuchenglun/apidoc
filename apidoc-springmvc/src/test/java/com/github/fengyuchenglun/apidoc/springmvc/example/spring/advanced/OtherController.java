package com.github.fengyuchenglun.apidoc.springmvc.example.spring.advanced;

import com.apigcc.model.Info;
import com.apigcc.model.InfoQuery;
import com.github.fengyuchenglun.apidoc.springmvc.example.common.*;
import com.github.fengyuchenglun.apidoc.springmvc.example.common.domain.model.query.UserForm;
import com.github.fengyuchenglun.apidoc.springmvc.example.common.domain.model.query.UserQuery;
import com.github.fengyuchenglun.apidoc.springmvc.example.common.domain.model.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 其他.
 */
@Controller
@RequestMapping("/other")
public class OtherController {

    /**
     * 默认页面，由于不是restful的，restdoc将忽略该Endpoint
     *
     * @return
     */
    @GetMapping
    public ModelAndView index() {
        return new ModelAndView();
    }

    /**
     * Hello with ResponseBody
     *  *********
     * 由于带有@ResponseBody，restdoc将解析该Endpoint
     * <p>
     * hhh
     * \*********
     *  *********
     * hhhh
     * *********
     * <p>
     * class ************** {
     * <p>
     * }
     *
     * @return
     */
    @GetMapping("/hello")
    @ResponseBody
    public UserVO hello() {
        return null;
    }

    /**
     * 未知的多泛型的tuple 演示
     *
     * @return tuple tuple
     */
    @GetMapping("/tuple")
    @ResponseBody
    public Tuple<UserForm, UserDTO> tuple() {
        return null;
    }

    /**
     * 多个RequestMethod
     * 暂时只支持取第一个
     *
     * @return result data
     */
    @RequestMapping(value = "/multiMethod", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public ResultData multiMethod() {
        return null;
    }


    /**
     * 多层返回结果.
     *
     * @param resultData the result data
     * @return the result data
     */
    @PostMapping("/multi")
    @ResponseBody
    public ResultData<Wrapper<UserDTO>> multi(@RequestBody ResultData<Wrapper<List<UserDTO>>> resultData) {
        return null;
    }

    /**
     * 引用二方Jar
     * 使用二方Jar的类时，代码解析器无法获取类上的注释，注解
     * 只能获取属性的名称和类型
     *
     * @param infoQuery the info query
     * @return info
     */
    @PostMapping("/jar")
    @ResponseBody
    public Info jar(@RequestBody InfoQuery infoQuery){
        return null;
    }

    /**
     * 一个复杂的类型 List<Map<String,User>>
     *
     * @return
     */
    @GetMapping("/map")
    @ResponseBody
    public List<Map<String, UserVO>> map() {
        return null;
    }

    /**
     * 一个更复杂的类型 List<Map<String,ResultData<Map<Integer,User>>>>
     *
     * @return
     */
    @GetMapping("/map")
    @ResponseBody
    public List<Map<String, ResultData<Map<Integer, User>>>> maps() {
        return null;
    }

    /**
     * 一个问号类型 List<Map<String,List<?>>>
     *
     * @return
     */
    @GetMapping("/map")
    @ResponseBody
    public List<Map<String, List<?>>> maps1() {
        return null;
    }

//    /**
//     * 多级菜单
//     * @return
//     */
//    @GetMapping("/menus")
//    @ResponseBody
//    public List<Menu> menus(){
//        return null;
//    }
//
//    /**
//     * 一个问号类型 List<Map<String,List<?>>>
//     * @return
//     */
//    @GetMapping("/map")
//    @ResponseBody
//    public List<Map<String,List<?>>> maps1(){
//        return null;
//    }
}
