@echo off
title Debug password-next-gen.jar
cd ../target/artifacts/password_next_gen_jar
java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 password-next-gen.jar
