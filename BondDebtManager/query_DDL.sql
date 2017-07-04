create user bonddebt identified by 1234
default tablespace users
temporary tablespace temp;
grant resource, connect to bonddebt;
 
------------------------------------------------------- 멤버 테이블 ------------------------------------------------------- 
CREATE TABLE t_member
(
    no          NUMBER          NOT NULL, 
    id          VARCHAR2(40)    NOT NULL, 
    password    VARCHAR2(40)    NULL, 
    name        VARCHAR2(40)    NULL, 
    tel         VARCHAR2(40)    NULL, 
    balance     NUMBER          NULL, 
    email       VARCHAR2(40)    NULL, 
    CONSTRAINT T_MEMBER_PK PRIMARY KEY (no)
);
CREATE SEQUENCE t_member_SEQ;

------------------------------------------------------- Group -------------------------------------------------------
CREATE TABLE t_group
(
    no          NUMBER          NOT NULL, 
    name        VARCHAR2(40)    NOT NULL, 
    reg_date    DATE            NOT NULL, 
    CONSTRAINT T_GROUP_PK PRIMARY KEY (no)
);
CREATE SEQUENCE t_group_SEQ;

------------------------------------------------------- Friend -------------------------------------------------------
CREATE TABLE t_friend
(
    member_no    NUMBER    NOT NULL, 
    friend_no    NUMBER    NOT NULL, 
    CONSTRAINT T_FRIEND_PK PRIMARY KEY (member_no, friend_no)
);
ALTER TABLE t_friend
    ADD CONSTRAINT FK_t_friend_member_no_t_member FOREIGN KEY (member_no)
        REFERENCES t_member (no);
ALTER TABLE t_friend
    ADD CONSTRAINT FK_t_friend_friend_no_t_member FOREIGN KEY (friend_no)
        REFERENCES t_member (no);
        
      
------------------------------------------------------- dealList -------------------------------------------------------
CREATE TABLE t_deal_list
(
    no             NUMBER           NOT NULL, 
    comments       VARCHAR2(1000)    NOT NULL, 
    sender_no      NUMBER           NOT NULL, 
    receiver_no    NUMBER           NOT NULL, 
    money          NUMBER           NOT NULL, 
    start_date     DATE             NOT NULL, 
    CONSTRAINT T_DEAL_LIST_PK PRIMARY KEY (no)
);
CREATE SEQUENCE t_deal_list_SEQ;
ALTER TABLE t_deal_list
    ADD CONSTRAINT FK_t_deal_list_sender_no_t_mem FOREIGN KEY (sender_no)
        REFERENCES t_member (no);
ALTER TABLE t_deal_list
    ADD CONSTRAINT FK_t_deal_list_receiver_no_t_m FOREIGN KEY (receiver_no)
        REFERENCES t_member (no);
        
        
------------------------------------------------------- history  -------------------------------------------------------
CREATE TABLE t_deal_history
(
    no             NUMBER           NOT NULL, 
    comments       VARCHAR2(1000)    NOT NULL, 
    sender_no      NUMBER           NOT NULL, 
    receiver_no    NUMBER           NOT NULL, 
    money          NUMBER           NOT NULL, 
    start_date     DATE             NOT NULL, 
    end_date       DATE             NOT NULL, 
    CONSTRAINT T_DEAL_HISTORY_PK PRIMARY KEY (no)
);
CREATE SEQUENCE t_deal_history_SEQ;

------------------------------------------------------- 그룹별 회원 테이블 -------------------------------------------------------
CREATE TABLE t_g_member_list
(
    group_no     NUMBER    NOT NULL, 
    member_no    NUMBER    NOT NULL, 
    CONSTRAINT T_G_MEMBER_LIST_PK PRIMARY KEY (group_no, member_no)
);
        
ALTER TABLE t_g_member_list
    ADD CONSTRAINT FK_t_g_member_list_member_no_t FOREIGN KEY (member_no)
        REFERENCES t_member (no);
        
ALTER TABLE t_g_member_list
    ADD CONSTRAINT FK_t_g_member_list_group_no_t_ FOREIGN KEY (group_no)
        REFERENCES t_group (no);
        

------------------------------------------------------- 채팅 테이블 -------------------------------------------------------   
CREATE TABLE t_chat
(
    no             NUMBER            NULL, 
    group_no       NUMBER            NOT NULL, 
    member_no      NUMBER            NOT NULL, 
    member_name    VARCHAR2(40)      NOT NULL, 
    message        VARCHAR2(1000)    NOT NULL, 
    chat_date      DATE              NOT NULL, 
    CONSTRAINT T_CHAT_PK PRIMARY KEY (no)
);     
CREATE SEQUENCE t_chat_SEQ;

ALTER TABLE t_chat
    ADD CONSTRAINT FK_t_chat_group_no_t_group_no FOREIGN KEY (group_no)
        REFERENCES t_group (no);
        

        
        

