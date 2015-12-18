%~d0
cd %~dp0
java -Xms256M -Xmx1024M -cp .;../lib/routines.jar;../lib/log4j-1.2.16.jar;../lib/dom4j-1.6.1.jar;../lib/talendcsv.jar;dummy_talend_0_1.jar; hism.dummy_talend_0_1.DUMMY_TALEND --context=Default %* 