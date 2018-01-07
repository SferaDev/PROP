# Root dir
ROOT_DIR := $(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))

# Source files
SOURCES := $(shell find $(ROOT_DIR)/src/ -name '*.java')
RESOURCES := $(ROOT_DIR)/src/resources/

# Handle the default make command with the program generation
all: clean
	mkdir -p $(ROOT_DIR)/out
	cd $(ROOT_DIR)/src/; javac -cp "$(ROOT_DIR)/.libs/*" $(SOURCES) -d $(ROOT_DIR)/out -encoding UTF8
	cd $(ROOT_DIR)/out/; ln -s $(ROOT_DIR)/data data; cd $(ROOT_DIR)

jar: all
	cd $(ROOT_DIR)/out/; jar cfe $(ROOT_DIR)/Mastermind.jar MainApplication .; cd $(ROOT_DIR)

run: all
	cd $(ROOT_DIR)/out/; java -cp ".:$(ROOT_DIR)/.libs/*" MainApplication; cd $(ROOT_DIR)/out/

runterminal: all
	cd $(ROOT_DIR)/out/; java -cp ".:$(ROOT_DIR)/.libs/*" MainApplication --terminal; cd $(ROOT_DIR)/out/

runjar:
	java -jar $(ROOT_DIR)/Mastermind.jar

clean:
	rm -rf $(ROOT_DIR)/out
	rm -rf $(ROOT_DIR)/Mastermind.jar
