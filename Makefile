# Root dir
ROOT_DIR := $(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))

# Final zip
ZIP = PROP_23.62.zip

all: clean
	mkdir -p $(ROOT_DIR)/entrega
	cp -R $(ROOT_DIR)/src/ $(ROOT_DIR)/entrega/
	cp $(ROOT_DIR)/build.make $(ROOT_DIR)/entrega/Makefile
	cp -R $(ROOT_DIR)/docs $(ROOT_DIR)/entrega/
	cp -R $(ROOT_DIR)/data $(ROOT_DIR)/entrega/
	cp -R $(ROOT_DIR)/libs $(ROOT_DIR)/entrega/.libs
	cp -R $(ROOT_DIR)/README.pdf $(ROOT_DIR)/entrega/
	cp -R $(ROOT_DIR)/members.txt $(ROOT_DIR)/entrega/
	cd $(ROOT_DIR)/entrega; zip -r $(ROOT_DIR)/$(ZIP) *

# Configuration for make clean
# We use "rm -rf" to recursively delete the files and not prompting the user if they don't exist
clean:
	rm -rf $(ROOT_DIR)/entrega
	rm -rf $(ROOT_DIR)/$(ZIP)
