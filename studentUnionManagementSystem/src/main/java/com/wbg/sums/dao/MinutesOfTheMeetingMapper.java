package com.wbg.sums.dao;

import com.wbg.sums.entity.MinutesOfTheMeeting;
import java.util.List;

public interface MinutesOfTheMeetingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table minutes_of_the_meeting
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer mId);
    int count();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table minutes_of_the_meeting
     *
     * @mbg.generated
     */
    int insert(MinutesOfTheMeeting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table minutes_of_the_meeting
     *
     * @mbg.generated
     */
    MinutesOfTheMeeting selectByPrimaryKey(Integer mId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table minutes_of_the_meeting
     *
     * @mbg.generated
     */
    List<MinutesOfTheMeeting> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table minutes_of_the_meeting
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MinutesOfTheMeeting record);

//    根据标题模糊查询 List<MinutesOfTheMeeting> selectTitle(title);
//    根据上传时间查询  List<MinutesOfTheMeeting> selectTitle(m_date);
}