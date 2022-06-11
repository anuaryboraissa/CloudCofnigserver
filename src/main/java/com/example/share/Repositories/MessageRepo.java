package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Messages;

public interface MessageRepo extends JpaRepository<Messages, Long> {
	@Query(value = "select * from message s where (s.std_from_id=?1 and s.std_to_id=?2 and (s.deleted=?3) and s.achieved=?4)",nativeQuery = true)
  Collection<Messages> getAllMsgSentById(long from,long to,int status1,int status2);
	@Query(value = "select * from message s where (s.std_from_id=?1 and s.std_to_id=?2 and (s.deleted=?3) and s.achieved=?4)",nativeQuery = true)
	Collection<Messages> getAllSendById(long from,long to,int status1,int status2);
	@Query(value = "select * from message s where (s.std_from_id=?1 and s.std_to_id=?2 and msg_id=?3)",nativeQuery = true)
	Messages getMessageByid(long from,long to,long msgid);
	@Query(value = "select * from message m where m.std_to_id=?1 and m.deleted=?2 and m.seen=?3 and m.achieved=?4",nativeQuery = true)
	Collection<Messages> totalMessagesReceivedById(long ownid,int status,int status1,int status2);
	@Query(value = "select * from message where std_from_id=?1 or std_to_id=?1",nativeQuery = true)
	Collection<Messages> allMSGSentOrReceivById(long ownid);
	@Query(value = "select * from message m where (m.std_to_id=?2 and m.std_from_id=?1) or (m.std_to_id=?1 and m.std_from_id=?2) and m.achieved=?3",nativeQuery = true)
	Collection<Messages> allAchievedById(long ownid,long fomid,int status);
	@Query(value = " select * from message m where (m.std_to_id=?1 or m.std_from_id=?1) and m.achieved=?2 and m.deleted=?3",nativeQuery = true)
	Collection<Messages> allAchievedById(long ownid,int status,int status1);
	@Query(value = "select * from message m where m.msg_id=?1",nativeQuery = true)
	Messages findMsgById(long id);
}
