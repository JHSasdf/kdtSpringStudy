package codingon.codingonspringbootjpa.service;

import codingon.codingonspringbootjpa.dto.UserDTO;
import codingon.codingonspringbootjpa.entity.UserEntity;
import codingon.codingonspringbootjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;



    public List<UserDTO> getUserList() {
        // repository 에서 전체 조회가 가능하도록
        // JPA에서 정의되어있는 메소드 사용
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDTO> result = new ArrayList<>();
        for(UserEntity userEntity: userEntityList) {
            UserDTO userDTO = UserDTO.builder()
                    .id(userEntity.getId())
                    .name(userEntity.getName())
                    .nickname(userEntity.getNickname())
                    .no(userEntity.getId()+100)
                    .build();
            result.add(userDTO);
        }

        return result;
    }

    public boolean checkName(String name) {
        boolean result = userRepository.existsByName(name);
        return result;
    }

    public String searchId(int id) {
        Optional<UserEntity> result = userRepository.findById(id);
        if(result.isPresent()) {
            return result.get().getName();
        } else {
            return "no user founded";
        }
    }
    public String insertUser(UserEntity user) {
        // jpa save(T) 메소드 : T 는 Entity
        // - insert 할 때 사용
        // - 기존 entity를 업데이트 할 때도 사용
        // => 기본값(pk) 상태에 따라 다르게 동작
        //      pk 가 존재한다면 pk 와 연결된 entity update
        //      pk 가 없는 경우, 새로운 entity insert

        UserEntity newUser = userRepository.save(user);
        // save를 했을 때 반환되는 객체는 Entity 객체
        return newUser.getName();
    }

    public List<UserDTO> searchName(String name) {
        List<UserEntity> users = userRepository.findByName(name);
        List<UserDTO> result = new ArrayList<>();
        for(UserEntity userEntity: users) {
            UserDTO userDTO = UserDTO.builder()
                    .id(userEntity.getId())
                    .name(userEntity.getName())
                    .nickname(userEntity.getNickname())
                    .no(userEntity.getId()+100)
                    .build();
            result.add(userDTO);
        }
        return result;
    }

    public List<UserDTO> searchNameOrNickname(String name) {
        List<UserEntity> users = userRepository.findByNameOrNickname(name, name);
        List<UserDTO> result = new ArrayList<>();
        for(UserEntity userEntity: users) {
            UserDTO userDTO = UserDTO.builder()
                    .id(userEntity.getId())
                    .name(userEntity.getName())
                    .nickname(userEntity.getNickname())
                    .no(userEntity.getId()+100)
                    .build();
            result.add(userDTO);
        }
        return result;
    }
}
