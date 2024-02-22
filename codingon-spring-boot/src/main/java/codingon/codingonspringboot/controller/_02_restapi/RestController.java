package codingon.codingonspringboot.controller._02_restapi;

import codingon.codingonspringboot.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.IntStream;

@Controller
public class RestController {
    // ================== Template 렌더링 ===================
    @GetMapping("/")
    public String getReq() {
        return "_02_restapi/req";
    }

    // ================== Get 요청 ========================
    // 매개변수 넘겨받는 방법
    // 1. /test?id=123              (쿼리)
    // 2. /test/123                 (params)

    @GetMapping("/get/res1")
    public String getRes1(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age, Model model) {
        // @RequestParam 어노테이션
        // - string query 중에서 name key 에 대한 value 를 String name 에 매핑 (?key=value)
        // - required=true 가 기본 값이므로 요청 URL 에서 name key 를 필수로 보내야 함.
        // ex. GET /get/res1?name=someone&age=someage
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "_02_restapi/res";
    }


    @GetMapping("/get/res2")
    public String getRes2(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "age", required = false) Integer age, Model model) {
        // required = false 옵션
        // - query string 에서 특정 key 를 optional하게 받아야 하는 경우
        // ex. 검색어 (필수) 해시태그 (선택)
        // - ?search=바나나
        // - ?search=바나나&hashtag=과일
        model.addAttribute("name", name);
        model.addAttribute("age", age); // 만약 인풋이 비어있으면 ""으로 와서 String으로 받았음,
        // 지금은 인풋 없애버리고 null값으로 들어올 거기 때문에 Integer로 바깠음
        return "_02_restapi/res";
    }

    @GetMapping("/get/res3/{param1}/{param2}")
    public String getRes3(@PathVariable(value = "param1") String param1, @PathVariable(value = "param2") int age, Model model) {
        // @PathVariable 어노테이션
        // - test/{id} 형식의 URL 경로로 넘어오는 변수로 값을 받을 때 사용
        // - 기본적으로 경로 변수는 값을 가져야 함 (값 없는 경우는 404 에러가 발생)
        // - 참고, uri 에 기입한 변수명과 다른 매개변수 이름을 사용하고 싶다면
        // - @PathVariable int age
        // - @PathVariable(value="param2") int age <= 이런 식으로 사용해야 함 (uri 랑 동일한 매개변수 사용 시 생략 가능)

        model.addAttribute("name", param1);
        model.addAttribute("age", age);
        return "_02_restapi/res";
    } // O

    @GetMapping({"/get/res4/{name}", "get/res4/{name}/{age}"}) // 선택적으로 받아오는 PathVariable이 있다면 경로를 여러개 설정
    public String getRes4(@PathVariable(value = "name") String name, @PathVariable(value ="age", required = false) Integer age, Model model) {
        // required = false 옵션
        // - name (필수), age (선택)
        // - optional 한 parameter 가 있다면 경로의 맨 뒤에 오도록 설정
        // 참고. Integer age 라고 한 이유
        // - age 는 optional 한 값. 즉 null 이 될 수 있기 때문에 primitive type 가 아닌 reference type 인 래퍼 객체 사용
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "_02_restapi/res";
    }

    @GetMapping("/introduce/{name}")
    public String getIntroduce(@PathVariable(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "_02_restapi/practice1";
    }

    @GetMapping("/introduce2")
    public String getIntroduce2(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "_02_restapi/practice2";
    }

    // ================== Post 요청 ========================
    // Post 로 전달할 때 Controller 에서 받는 방법은 @RequestParam
    @PostMapping("/post/res1")
    public String postRes1(@RequestParam String name, @RequestParam int age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "_02_restapi/res";
    } // O

    @PostMapping("/post/res2")
    public String postRes2(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "_02_restapi/res";
    } // O

    // ㄴ 여기까지 코드는 return 이 항상 Template View! 하지만 API 에서 데이터 자체를 응답하고 싶다면?
    // => @ResponseBody 어노테이션 사용
    @PostMapping("/post/res3")
    @ResponseBody
    public String postRes3(@RequestParam String name, @RequestParam int age, Model model) {
        // @ResponseBody 어노테이션
        // - 응답시 객체를 JSON 으로 리턴할 때 사용 (직렬화, serialize)
        // - 즉, 응답 객체를 전달 (express의 res.json 과 유사)
        model.addAttribute("name", name);
        model.addAttribute("age", age);
//        return "_02_restapi/res"; // @ResponseBody가 붙으면 템플릿 엔진 리턴이 아닌 res.send() 처럼 문자열 그 자체 응답
        return name + " " + age;
        // json 형식으로 보내려면 그냥 객체로 묶어서 return 해줘야함. 근데 addAttribute로 해주면 되나? 는 아닌가
    } // O

    @GetMapping("/restapi/practice/post")
    public String getPractice3(Model model) {
        model.addAttribute("years", IntStream.range(1950, 2025).boxed().toList());
        model.addAttribute("months", IntStream.range(1, 13).boxed().toList());
        model.addAttribute("days", IntStream.range(1, 32).boxed().toList());
        return "_02_restapi/practice3";
    }

    @PostMapping("/restapi/practice/result")
    public String postPractice3(@RequestParam String name, @RequestParam Integer age, @RequestParam String gender,
                                @RequestParam String year, @RequestParam String month, @RequestParam String day,
                                @RequestParam(required = false) boolean trip, @RequestParam(required = false) boolean fassion, @RequestParam(required = false) boolean food,
                                Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("gender", gender);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("trip", trip);
        model.addAttribute("fassion", fassion);
        model.addAttribute("food", food);
        return "_02_restapi/practice3Result";
    }

    // ======================= DTO 이용 ==============================
    // 1. Get 요청
    @GetMapping("/dto/res1")
    @ResponseBody
    public String dtoRes1(@ModelAttribute UserDTO userDTO) {
        // 변수로 값을 하나씩 가져오는 것이 아니라 DTO 객체에 값을 담아서 가져오기
        // @ModelAttribute: HTML 폼 데이터를 컨트롤러로 전달할 때 객체 매핑하는 어노테이션
        // -> 매핑: setter 함수 실행
        // ex. ?name=홍길동&age=20 -> setName(), setAge() 실행해서 값 매핑
        // **************** Lombok plugin 설치해야 에러 안남 **********
        // - 왜 에러? 롬복은 애플리케이션 실행 후에 getter, setter 를 생성해 주기 때문에 즉, 이 시점에는 getter가 없다고 판단해서 에러가 남
        return userDTO.getName() + " " + userDTO.getAge();
    } // (O)


    // 2. Post 요청
    @PostMapping("/dto/res2")
    @ResponseBody
    public String dtoRes2(UserDTO userDTO) {
        // @ModelAttribute 어노테이션이 없을 떄에는 자동 추가됨, (생략 가능)
        return userDTO.getName() + " " + userDTO.getAge();
    } // (O)

    // 2. Post 요청
    @PostMapping("/dto/res3")
    @ResponseBody
    public String dtoRes3(@RequestBody UserDTO userDTO) {
        // @RequestBody 어노테이션 
        // - 요청의 본문에 있는 데이터 (req.body)를 읽어와서 객체에 매핑
        // - 여기서 매핑? 필드 값에 값을 주입
        // - 전달 받은 요청의 형식이 json 또는 xml 일 때만 실행 가능
        // - POST /dto/res3 요청의 경우 "일반 폼 전송" 임 (www.x-form-urlencoded).
        // => xml / json이 아니기 떄문에 @RequestBody 어노테이션 사용 시 오류 발생함
        return userDTO.getName() + " " + userDTO.getAge();
    } // (X) error (type=Unsupported Media Type, status=415)
}
