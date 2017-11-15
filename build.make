# Source files
SOURCES := $(shell find . -name '*.java')

# Handle the default make command with the program generation
all:
	javac -classpath ".lib/*" $(SOURCES)

run:
	java Mastermind -classpath ".lib/*"

clean:
	rm -rf $(CLASSES:.java=.class)
