DROP TABLE IF EXISTS WEBEX;
 
CREATE TABLE WEBEX (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  GUEST_NAME VARCHAR(250) NOT NULL,
  AGENT_NAME VARCHAR(250) NOT NULL,
  AGENT_CODE VARCHAR(10) NOT NULL,
  WEBEX_HOST VARCHAR(250) NOT NULL,
  WEBEX_JWT VARCHAR(500) NOT NULL,  
  GUEST_URL VARCHAR(250) NULL, 
  MEETING_UUID VARCHAR(10) NULL
);
