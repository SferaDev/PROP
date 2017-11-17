# Root dir
ROOT_DIR := $(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))

# Final zip
ENTREGA = PROP_23_62
ZIP = PROP_23_62.zip

all: clean
	mkdir -p $(ROOT_DIR)/$(ENTREGA)
	cp -R $(ROOT_DIR)/src/ $(ROOT_DIR)/$(ENTREGA)/
	cp $(ROOT_DIR)/build.make $(ROOT_DIR)/$(ENTREGA)/Makefile
	cp -R $(ROOT_DIR)/docs $(ROOT_DIR)/$(ENTREGA)/
	cp -R $(ROOT_DIR)/data $(ROOT_DIR)/$(ENTREGA)/
	cp -R $(ROOT_DIR)/libs $(ROOT_DIR)/$(ENTREGA)/.libs
	cp -R $(ROOT_DIR)/README.pdf $(ROOT_DIR)/$(ENTREGA)/
	cp -R $(ROOT_DIR)/members.txt $(ROOT_DIR)/$(ENTREGA)/
	cp -R $(ROOT_DIR)/test $(ROOT_DIR)/$(ENTREGA)/
	cd $(ROOT_DIR); zip -r $(ROOT_DIR)/$(ZIP) $(ENTREGA)/

# Configuration for make clean
# We use "rm -rf" to recursively delete the files and not prompting the user if they don't exist
clean:
	rm -rf $(ROOT_DIR)/$(ENTREGA)
	rm -rf $(ROOT_DIR)/$(ZIP)
