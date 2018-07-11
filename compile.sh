# Compile project
printf "Compiling..."               # Prompt start of compilation
mkdir -p bin                        # Create /bin/ if it does not exist
rm -rf bin/*                        # Empty the /bin/ directory if there is already contents inside
find -name "*.java" > sources.txt   # Find all source files recursively and output their paths in sources.txt
javac -d bin @sources.txt           # Compile all source files in their respective locations in /bin/ as root
rm sources.txt                      # Delete sources.txt
printf "DONE\n"                     # Prompt end of compilation
