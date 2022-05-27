package com.example.individualproject.business.impl;

import com.example.individualproject.business.exception.*;
import com.example.individualproject.dto.login.AccessTokenDTO;
import com.example.individualproject.dto.users.*;
import com.example.individualproject.business.UserService;
import com.example.individualproject.repository.AdminRepository;
import com.example.individualproject.repository.NormalUserRepository;
import com.example.individualproject.repository.entity.Admin;
import com.example.individualproject.repository.entity.NormalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final NormalUserRepository normalUserRepository;
    private final AdminRepository adminRepository;
    private final AccessTokenDTO requestAccessToken;

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

        if (!requestAccessToken.hasRole("NORMALUSER")){
            throw new InvalidCredentialsException();
        }

        if (!requestAccessToken.getUserId().equals(id)){
            throw new InvalidCredentialsException();
        }


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

    //Delete
    @Override
    public  boolean deleteUser(String username){
        NormalUser normalUserResult = normalUserRepository.findByUsername(username);

        if(normalUserResult == null) {
            throw new UserNotFoundException();
        }

        normalUserRepository.deleteById(normalUserResult.getId());
        return true;
    }
    //Update
    @Override
    public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateRequestDTO){

        if (!requestAccessToken.hasRole("NORMALUSER")){
            throw new InvalidCredentialsException();
        }

        if (!requestAccessToken.getUserId().equals(updateRequestDTO.getId())){
            throw new InvalidCredentialsException();
        }

        NormalUser user = normalUserRepository.findAllByIdIs(updateRequestDTO.getId());
        if(user == null) {
            return null;
        }

        if(!(user.getEmail().equals(updateRequestDTO.getEmail())) && normalUserRepository.existsByEmail(updateRequestDTO.getEmail())){
            throw new EmailAlreadyExistsExeption();
        }

        if(!(user.getPhonenumber().equals(updateRequestDTO.getPhoneNumber())) && normalUserRepository.existsByPhonenumber(updateRequestDTO.getPhoneNumber())){
            throw new PhoneNumberAlreadyExistsExeption();
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

    @Override
    public boolean isUsernameUnique(String name) {
        boolean result = true;

        if(normalUserRepository.existsByUsername(name) || adminRepository.existsByUsername(name))  {
            result = false;
        }

        return result;
    }

    @Override
    public boolean isPhoneNumberUnique(String name) {
        return normalUserRepository.existsByPhonenumber(name);
    }

    @Override
    public boolean isEmailUnique(String name) {
        return normalUserRepository.existsByEmail(name);
    }

    //Add
    @Override
    public CreateUserResponseDTO addUser(CreateUserRequestDTO createRequestDTO){
        if(normalUserRepository.existsByUsername(createRequestDTO.getUsername()) || adminRepository.existsByUsername(createRequestDTO.getUsername()) ) {
            throw new UsernameAlreadyExistsExeption();
        }
        if(normalUserRepository.existsByEmail(createRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsExeption();
        }
        if( normalUserRepository.existsByPhonenumber(createRequestDTO.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsExeption();
        }

        NormalUser newUser = new NormalUser(createRequestDTO);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        normalUserRepository.save(newUser);

        return CreateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();
    }
}

