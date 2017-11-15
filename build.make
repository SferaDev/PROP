# Root dir
ROOT_DIR := $(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))

# Source files
SOURCES := $(shell find $(ROOT_DIR) -name '*.java')

# Handle the default make command with the program generation
all: clean
	mkdir -p $(ROOT_DIR)/out
	cd $(ROOT_DIR)/src/; javac -cp "$(ROOT_DIR)/.lib/*" $(SOURCES) -d $(ROOT_DIR)/out
	cd $(ROOT_DIR)/out/; ln -s $(ROOT_DIR)/data data; cd $(ROOT_DIR)

jar: all
	cd $(ROOT_DIR)/out/; jar cfe $(ROOT_DIR)/Mastermind.jar Main .; cd $(ROOT_DIR)

run:
	cd $(ROOT_DIR)/out/; java -cp ".:$(ROOT_DIR)/.lib/*" Main; cd $(ROOT_DIR)/out/

runjar:
	java -jar $(ROOT_DIR)/Mastermind.jar

clean:
	rm -rf $(ROOT_DIR)/out
	rm -rf $(ROOT_DIR)/Mastermind.jar
