package org.yunyangu.yunyangu.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yunyangu.yunyangu.entity.Message;

import java.util.List;

import static java.time.LocalTime.now;

@Mapper
public interface MessageMapper {

    @Insert("insert into Message(content,msg_from,msg_to,msg_type,msg_time)  " +
            "values(#{message.content}," +
            "#{message.msgFrom}," +
            "#{message.msgTo}," +
            "'1', " +
            "now())")
    int sendUserMessage(Message message);

    @Insert("insert into Message(content,msg_from,msg_to,msg_type,msg_time)  " +
            "values(#{message.content}," +
            "#{message.msgFrom}," +
            "#{message.msgTo}," +
            "'2', " +
            "now())")
    int sendGroupMessage(Message message);

    @Select("SELECT * FROM Message WHERE " +
            "((msg_from = #{userID} AND msg_to = #{friendID}) OR " +
            "(msg_from = #{friendID} AND msg_to = #{userID})) AND " +
            "msg_type = 1")
    List<Message> getUserHistory(int userId, int friendId);

    @Select("SELECT * FROM Message WHERE " +
            "(msg_from = #{userID} AND msg_to = #{groupId}) AND " +
            "msg_type = 2")
    List<Message> getGroupHistory(int userId, int groupId);

    @Delete("DELETE FROM Message WHERE msg_id = #{messageId}")
    Integer deleteMessage(int messageId);
}
