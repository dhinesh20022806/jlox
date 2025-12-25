SRC = com/interpreter/lox
OUT = out

MAIN = com.interpreter.lox.Lox

.PHONY: build run clean debug

build:
	javac -g -d $(OUT) $(SRC)/*.java

run: build
	java -cp ${OUT} $(MAIN)

debug: build
	java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5505 -cp $(OUT) $(MAIN)

clean:
	rm -rf $(OUT)

file:
	@echo "Creating file ${FILE_NAME} in ./com/interpreter/lox";
	@touch ./com/interpreter/lox/${FILE_NAME}.java