%~d0
cd %~dp0
java -Xms256M -Xmx1024M -cp classpath.jar; hism.dummy_talend_0_1.DUMMY_TALEND --context=Default %* 