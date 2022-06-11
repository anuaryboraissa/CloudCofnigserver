package com.example.share.Services.Implement;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.share.Controller.DTOS.GroupDTO;
import com.example.share.Controller.DTOS.MessageDTO;
import com.example.share.Entities.Files;
import com.example.share.Entities.Groups;
import com.example.share.Entities.Messages;
import com.example.share.Entities.Requests;
import com.example.share.Entities.Roles;
import com.example.share.Entities.Student;
import com.example.share.Repositories.GroupRepo;
import com.example.share.Repositories.MessageRepo;
import com.example.share.Repositories.RequestRepostry;
import com.example.share.Repositories.RoleRepo;
import com.example.share.Repositories.StudentRepostry;

@Service
public class StudentsServices {
	@Autowired
	private StudentRepostry studeRepo;
	@Autowired
	private RoleRepo roles;
	@Autowired
	private RequestRepostry reqRepo;
	@Autowired
	private MessageRepo msgRepo;
	@Autowired
	private GroupRepo grprepo;
	
    public Collection<Student> selectStudents(Student student,String email) {
    	return studeRepo.findStudentsByEmail(email);
    }
    public Student selectStudent(String email) {
    	return studeRepo.findByEmail(email);
    }
    
    public Student saveRequests(String e1,long id,int status) {
    	Student student=studeRepo.findByEmail(e1);
    	System.out.println(student);
    	System.out.println(id);
    	Student std2=studeRepo.findStById(id);
        Requests request=new Requests();
    		request.setStatus(status);
    		request.setStudentfrom(student);
    		request.setStudentTo(std2);
    		reqRepo.save(request);
    		System.out.println("duuh");
		return student;	
    }
    public Collection<Messages> getSentMsgs(long from,long to,int status1,int status2) {
		return msgRepo.getAllMsgSentById(from,to,status1,status2);
    	
    }
    public Messages getMsgById(long id) {
  		return msgRepo.findMsgById(id);
      	
      }
    public Collection<Messages> allreciivOrSent(long ownid) {
  		return msgRepo.allMSGSentOrReceivById(ownid);
    }
    public Collection<Messages> getSendBy(long from,long to,int status,int status2) {
		return msgRepo.getAllSendById(from, to, status,status2);
    }
    public Collection<Messages> getAllMessagesReceived(long ownid,int status,int status1,int status2) {
  		return msgRepo.totalMessagesReceivedById(ownid, status,status1,status2);
      }
    public Collection<Messages> getAllAchievedReceived(long ownid,int status1,int status2) {
  		return msgRepo.allAchievedById(ownid,status1,status2);
      }
    public Collection<Messages> updateArchieve(long ownid,long fromid,int status1) {
    	Collection<Messages> msgs=msgRepo.allAchievedById(ownid, fromid, status1);
    	System.out.println("to "+ownid+" from "+fromid+" status "+status1);
    	if(status1==0) {
    		if(!msgs.isEmpty()) {
        		for (Messages messages : msgs) {
            		messages.setAchieved(1);
            		msgRepo.save(messages);
        		}
        		return msgs;
        	}
    	}
    	if(status1==1) {
    		if(!msgs.isEmpty()) {
        		for (Messages messages : msgs) {
            		messages.setAchieved(0);
            		msgRepo.save(messages);
        		}
        		return msgs;
        	}
    	}
    
    	else {
    		System.out.println("error occur");
    		
    	}
		return null;
  		
      }
    public Messages deletemsgByid(long from,long to,long msgid) {
    	System.out.println("kutoka "+from+" kwenda "+to);
    	Messages msg=msgRepo.getMessageByid(from, to, msgid);
    	if(msg!=null) {
    		msg.setDeleted(1);
    		msgRepo.save(msg);
    		return msg;
    	}
    	else return null;	
    }
    public Student saveMessage(MessageDTO msg,String e1,long toid) {
    	LocalTime localTime=LocalTime.now(ZoneId.of("GMT+02:59"));
    	Student student=studeRepo.findByEmail(e1);
    	Student tostd2=studeRepo.findStById(toid);
    	System.out.println("from "+student);
    	System.out.println("To "+tostd2);
    	Messages message=new Messages();
    	message.setMsg(msg.getMsg());
    	message.setDeleted(0);
    	message.setSeen(0);
    	message.setTime(localTime.getHour()+":"+localTime.getMinute());
    	message.setAchieved(0);
    	message.setStdFrom(student);
    	message.setStdTo(tostd2);
    	msgRepo.save(message);
    	System.out.println(msg.getMsg());
    	System.out.println("mgs saved");
		return student;	
    }

    public Student findStudent(long id) {
    	return studeRepo.findStById(id);
    }
    public Collection<Student> selectRequests(int status,long ownid) {
		return studeRepo.findStudRequestByStatus(status, ownid);	
    }
    public Collection<Student> selectAll() {
		return studeRepo.findAll();	
    }
    public Collection<Student> selectNewFriends(String email,int status,long toid) {
 		return studeRepo.findSuggestedStudNewFriedsByStatus(email, status,toid);	
     }
    public Collection<Student> selectBlockedFriends(String email,int status,long toid) {
  		return studeRepo.findBlockedstudentsByStatus(email, status, toid);	
      }
    public Collection<Student> whoConfirmRequest(String email,int status,long fromid) {
 		return studeRepo.findwhoConfirmFriedsByStatus(email, status, fromid);	
     }
    public Collection<Student> whoSendMsgToMe(long ownid,int status,int status1) {
 		return studeRepo.findmgsWhoSendToMeById(ownid,status,status1);
     }
    public Collection<Student> archieved(long ownid,int status,int status1) {
 		return studeRepo.findmgsArchievedById(ownid, status,status1);
     }

    public Collection<Student> selectSuggestedRequests(String email,int status,int status1,int status2,int status3,long ownid) {
		return studeRepo.findSuggestedStudRequestByStatus(email,status,status1,status2,status3, ownid);	
    }
    public Student editRequestStatus(long toid,long fromid,int status) {
    	Student student=studeRepo.editRequestStatus(toid,fromid);
    	System.out.println(toid+" "+fromid);
    	Student std2=studeRepo.findStById(toid);
    	Student stdf=studeRepo.findStById(fromid);
    	Requests req=reqRepo.selectRequestById(toid, fromid,1);
    	Requests req2=reqRepo.selectRequestById(toid, fromid,0);
    	Requests req3=reqRepo.selectRequestById(toid, fromid,3);
    	Requests req4=reqRepo.selectRequestById(toid, fromid,2);
    	Requests request=new Requests();
    	if(status==4) {
    		if(req4!=null) {
    			req4.setStudentfrom(student);
    			req4.setStatus(status);
    			req4.setStudentTo(std2);
    			reqRepo.save(req4);
    		}
    		else {
    			request.setStudentfrom(std2);
    			request.setStatus(status);
    			request.setStudentTo(stdf);
    			reqRepo.save(request);
    		}	
    		return stdf;
		}
    	else if(status==3) {
    		req2.setStudentfrom(student);
    		req2.setStatus(status);
    		req2.setStudentTo(std2);
    		reqRepo.save(req2);
    	}
    	else if(status==2) {
    		req.setStudentfrom(student);
    		req.setStatus(status);
    		req.setStudentTo(std2);
    		reqRepo.save(req);
    	}
    	else if(status==0) {
    		if(req3!=null) {
    			req3.setStudentfrom(student);
        		req3.setStatus(status);
        		req3.setStudentTo(std2);
        		reqRepo.save(req3);
    		}
    		else {
    			req.setStudentfrom(student);
        		req.setStatus(status);
        		req.setStudentTo(std2);
        		reqRepo.save(req);
    		}
    	
		   }
    	else 
    	{   request.setStudentfrom(student);
    		request.setStatus(status);
    		request.setStudentTo(std2);
    		reqRepo.save(request);
    	}
    	
			return student;
    	
    }
  public Groups saveGroup(GroupDTO groupDTO,Student student) {
    Student std=studeRepo.findStById(student.getId());
    System.out.println("std ni "+std.getFirst());
    Groups groups=new Groups();
    groups.setCapacity(groupDTO.getCapacity());
    groups.setGrp_desc(groupDTO.getGrp_desc());
    groups.setGrp_name(groupDTO.getGrp_name());
    groups.setStudent(groupDTO.getStudent());
    groups.setGrp_admin(groupDTO.getGrp_admin());
    groups.setGrp_icon(groupDTO.getGrp_icon());
   Groups grp=grprepo.save(groups);
    return grp;
    	
    }

}
