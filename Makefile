all: clean
	mkdir -p entrega
	cp -R src/* entrega/
	cp build.make entrega/Makefile
	cp -R docs entrega/
	cp -R data entrega/
	cp -R lib entrega/.lib

# Configuration for make clean
# We use "rm -rf" to recursively delete the files and not prompting the user if they don't exist
clean:
	rm -rf entrega
