package com.doctorbari.serviceImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctorbari.dto.DoctorDropDownDto;
import com.doctorbari.dto.ProfileDto;
import com.doctorbari.dto.RegistrationDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.helper.SendEmail;
import com.doctorbari.model.DoctorDetails;
import com.doctorbari.model.Role;
import com.doctorbari.model.User;
import com.doctorbari.repository.DoctorDetailsRepository;
import com.doctorbari.repository.RoleRepository;
import com.doctorbari.repository.UserRepository;
import com.doctorbari.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private SendEmail sendEmail;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private DoctorDetailsRepository doctorDetailsRepository;

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public CommonMsg updateProfileInfo(ProfileDto profileDtom) {
		CommonMsg commonMsg = new CommonMsg();
		User user = userRepository.findByUserName(profileDtom.getUserName());
		user.setFirstname(profileDtom.getFirstname());
		user.setEmail(profileDtom.getEmail());
		if (!profileDtom.getNewPassword().isEmpty()) {
			user.setPassword(passwordEncoder.encode(profileDtom.getNewPassword()));
		}
		userRepository.save(user);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public CommonMsg resetPassword(User user) {
		CommonMsg commonMsg = new CommonMsg();
		User checkUser = userRepository.findByemail(user.getEmail());
		User user1 = new User();
		if (checkUser != null) {
			String validEmail = user.getEmail();
			int rnd = (int) (Math.random() * 100);
			String rndstring = Integer.toString(rnd);
			String getstore = validEmail.substring(0, 7);
			String passwordAssistance = getstore + rndstring;
			String Info = "Doctor Bari LTD" + "\n\n";
			Info += "Hey, "
					+ "Hopefully you are doing well.Thank you so much for contacting us!!" + "\n";
			Info += "Your new password: " + passwordAssistance + "\n\n\n";
			Info += "If you have any problem.Please contact us" + "\n";
			Info += "Phone: +88 01812566856" + "\n";
			Info += "Gmail: 1000145@daffodil.ac";
			user1 = userRepository.findByemail(user.getEmail());
			user1.setPassword(passwordEncoder.encode(passwordAssistance));
			userRepository.save(user1);
			sendEmail.send(validEmail, "Forgot Password", Info, "masudrana.diit@gmail.com", "MasudRana@diit8718");
			commonMsg.setMsgCode("200");
		}else {
			commonMsg.setMsgCode("201");
		}
		return commonMsg;
	}

	@Override
	public CommonMsg doctorSetup(RegistrationDto regDto) {

		CommonMsg commonMsg = new CommonMsg();
		User user = new User();
		if (regDto.getUpdateId().isEmpty()) {

			User checkUserName = userRepository.findByUserName(regDto.getUserName());
			User checkEmail = userRepository.findByemail(regDto.getEmail());
			User checkPhone = userRepository.findByphone(regDto.getPhone());
			if (checkUserName != null) {
				commonMsg.setMsgCode("exitName");
				return commonMsg;
			}
			if (checkEmail != null) {
				commonMsg.setMsgCode("exitEmail");
				return commonMsg;
			}
			if (checkPhone != null) {
				commonMsg.setMsgCode("exitPhone");
				return commonMsg;
			}
			user.setUserName(regDto.getUserName());
			user.setFirstname(regDto.getFirstname());
			user.setEmail(regDto.getEmail());
			user.setPhone(regDto.getPhone());
			user.setRolename("ROLE_DOCTOR");
			user.setRoles(Arrays.asList(roleRepository.findByname("ROLE_DOCTOR")));
			user.setDate(new Date());
			user.setPassword(passwordEncoder.encode(regDto.getPassword()));
			userRepository.save(user);
			commonMsg.setMsgCode("200");
		} else {
			user = userRepository.findByUserid(Long.parseLong(regDto.getUpdateId()));
			user.setUserName(regDto.getUserName());
			user.setFirstname(regDto.getFirstname());
			user.setEmail(regDto.getEmail());
			user.setPhone(regDto.getPhone());
			user.setDate(new Date());
			if (!regDto.getPassword().isEmpty()) {
				user.setPassword(passwordEncoder.encode(regDto.getPassword()));
			}
			userRepository.save(user);
			commonMsg.setMsgCode("205");
		}
		return commonMsg;
	}

	@Override
	public CommonMsg userReg(RegistrationDto regDto) {
		
		CommonMsg commonMsg = new CommonMsg();
		User user = new User();
		if(regDto.getUpdateId().isEmpty()) {
			
			User checkUserName = userRepository.findByUserName(regDto.getUserName());
			User checkEmail = userRepository.findByemail(regDto.getEmail());
			long countRow=userRepository.count();
			if (checkUserName != null) {
				commonMsg.setMsgCode("exitName");
				return commonMsg;
			}

			if (checkEmail != null) {
				commonMsg.setMsgCode("exitEmail");
				return commonMsg;
			}
			
			user.setUserName(regDto.getUserName());
			user.setFirstname(regDto.getFirstname());
			user.setEmail(regDto.getEmail());
			user.setPhone(regDto.getPhone());
			
			if(countRow==0) {
				user.setRolename("ROLE_ADMIN");
				user.setRoles(Arrays.asList(roleRepository.findByname("ROLE_ADMIN")));
			}else {
				user.setRolename("ROLE_USER");
				user.setRoles(Arrays.asList(roleRepository.findByname("ROLE_USER")));
			}
			user.setDate(new Date());
			user.setPassword(passwordEncoder.encode(regDto.getPassword()));
			userRepository.save(user);
			commonMsg.setMsgCode("200");
		}else {
			user=userRepository.findByUserid(Long.parseLong(regDto.getUpdateId()));
			user.setFirstname(regDto.getFirstname());
			user.setEmail(regDto.getEmail());
			user.setUserName(regDto.getUserName());
		    if(!regDto.getPassword().isEmpty()) {
				user.setPassword(passwordEncoder.encode(regDto.getPassword()));
			}
		    userRepository.save(user);
		    commonMsg.setMsgCode("205");
		}
		return commonMsg;
	}

	@Override
	public List<User> doctorList() {
		return userRepository.findByrolename("ROLE_DOCTOR");
	}

	@Override
	public List<User> UserList() {
		return userRepository.findByrolename("ROLE_USER");
	}

	@Override
	public CommonMsg deleteUser(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		userRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<User> dropDownDoctorList() {
		return userRepository.dropDownDoctorList();
	}

	@Override
	public CommonMsg deleteDoctor(Long userId) {
		CommonMsg commonMsg = new CommonMsg();
		userRepository.deleteById(userId);
		DoctorDetails checkDate = doctorDetailsRepository.findByuserId(userId);
		if (checkDate != null) {
			doctorDetailsRepository.deleteByUserId(userId);
		}

		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<DoctorDropDownDto> getDoctorList() {
		return userRepository.getDoctorList();
	}

	@Override
	public long countByrolename(String roleName) {
		return userRepository.countByrolename(roleName);
	}

}
