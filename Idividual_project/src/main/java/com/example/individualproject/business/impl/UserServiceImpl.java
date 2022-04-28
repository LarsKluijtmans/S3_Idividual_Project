package com.example.individualproject.business.impl;

import com.example.individualproject.dto.users.*;
import com.example.individualproject.business.UserService;
import com.example.individualproject.repository.AdminRepository;
import com.example.individualproject.repository.NormalUserRepository;
import com.example.individualproject.repository.entity.Admin;
import com.example.individualproject.repository.entity.NormalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService { 

    private final NormalUserRepository normalUserRepository;
    private final AdminRepository adminRepository;

    @Override
    public List<GetUserDTO> getAllUsers(){
        List<GetUserDTO> result = new ArrayList<>();

        List<NormalUser> normalUsersResult = normalUserRepository.findAll();
        List<Admin> adminResult = adminRepository.findAll();

        for (NormalUser user: normalUsersResult) {
            result.add(new GetUserDTO(user));
        }
        for (Admin user: adminResult) {
            result.add(new GetUserDTO(user));
        }

        return result;
    }
    @Override
    public List<GetUserDTO> getAllNormalUsers(){
        List<GetUserDTO> result = new ArrayList<>();

        List<NormalUser> normalUsersreuslt = normalUserRepository.findAll();

        for (NormalUser user: normalUsersreuslt) {
            result.add(new GetUserDTO(user));
        }

        return result;
    }
    @Override
    public List<GetUserDTO> getAllAdmins(){
        List<GetUserDTO> result = new ArrayList<>();

        List<Admin> adminResult = adminRepository.findAll();

        for (Admin user: adminResult) {
            result.add(new GetUserDTO(user));
        }

        return result;
    }
    @Override
    public GetUserDTO getUserByID(Long id){

        NormalUser normalUserResult = normalUserRepository.findAllByIdIs(id);

        if(normalUserResult != null) {
            return new GetUserDTO(normalUserResult);
        }
        else {
            Admin adminResult = adminRepository.findAllByIdIsLike(id);
           if(adminResult != null) {
               return new GetUserDTO(adminResult);
           }
        }
        return null;
    }
    @Override
    public List<GetUserDTO> getAllUserByName(String name){
        List<GetUserDTO> result = new ArrayList<>();

        String searchName = "%"+name+"%";
        List<NormalUser> normalUsersResult = normalUserRepository.findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike(searchName,searchName,searchName);
        List<Admin> adminResult = adminRepository.findAllByUsernameIsLike(searchName);

        for (NormalUser user: normalUsersResult) {
            result.add(new GetUserDTO(user));
        }
        for (Admin user: adminResult) {
            result.add(new GetUserDTO(user));
        }

        return result;
    }
    @Override
    public GetLoginDTO VerifyLoginCredentails(String username, String password){
       NormalUser user = normalUserRepository.getUserByUsernameIsAndPasswordIs(username, password);

       if(user == null) {
           Admin admin = adminRepository.getUserByUsernameIsAndPasswordIs(username, password);
           return new GetLoginDTO(admin,"admin token");
       }else{
           return new GetLoginDTO(user,"normal token");
       }
    }
    @Override
    public boolean isUsernameUnique(String name) {

        boolean result = true;

      if(normalUserRepository.findByUsernameIs(name) != null  || adminRepository.findByUsernameIs(name) != null) {
          result = false;
      }
          return result;
    }
    @Override
    public boolean isEmailUnique(String email) {
        boolean result = true;

        if(normalUserRepository.findByEmailIs(email) != null) {
            result = false;
        }
        return result;
    }
    @Override
    public boolean isPhoneNumberUnique(String phoneNumber) {

        boolean result = true;

        if(normalUserRepository.findByPhonenumberIs(phoneNumber) != null) {
            result = false;
        }
        return result;
    }

    //Delete
    @Override
    public  boolean deleteUser(Long id){
        NormalUser normalUserResult = normalUserRepository.findAllByIdIs(id);

        if(normalUserResult != null) {
            normalUserRepository.deleteById(id);
            return true;
        }

        return false;
    }
    //Update
    @Override
    public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateRequestDTO){

        NormalUser user = normalUserRepository.findAllByIdIs(updateRequestDTO.getId());
        if(user == null) {
            return null;
        }

        NormalUser repeated = normalUserRepository.findByPhonenumberIs(updateRequestDTO.getPhoneNumber());

        //If repeated == null then the phoneNumber is not used by anyone else yet
        //If the phoneNumber is still the same one this user had before continue
        if(repeated != null && !(user.getPhonenumber().equals(updateRequestDTO.getPhoneNumber()))) {
            return null;
        }

        NormalUser repeated1 = normalUserRepository.findByEmailIs(updateRequestDTO.getEmail());

        //If repeated1 == null then the email is not used by anyone else yet
        //If the email is still the same one this user had before continue
        if(repeated1 != null && !(user.getEmail().equals(updateRequestDTO.getEmail()))) {
            return null;
        }

        NormalUser newUser = new NormalUser(
                updateRequestDTO,
                user.getUsername(),
                user.getPassword(),
                user.getProductsSelling());

        normalUserRepository.save(newUser);

        return UpdateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();

    }
    //Add
    @Override
    public CreateUserResponseDTO addUser(CreateUserRequestDTO createRequestDTO){
        NormalUser repeated1 = normalUserRepository.findByUsernameIs(createRequestDTO.getUsername());
        Admin repeated2 = adminRepository.findByUsernameIs(createRequestDTO.getUsername());

        if(repeated1 != null || repeated2 != null) {
            return null;
        }

        NormalUser repeated3 = normalUserRepository.findByPhonenumberIs(createRequestDTO.getPhoneNumber());
        NormalUser repeated4 = normalUserRepository.findByEmailIs(createRequestDTO.getEmail());

        if(repeated3 != null || repeated4 != null) {
            return null;
        }

        NormalUser newUser = new NormalUser(createRequestDTO);

        normalUserRepository.save(newUser);

        return CreateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();
    }
}

