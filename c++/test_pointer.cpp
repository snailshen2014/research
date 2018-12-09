#include<iostream>

char* GetString1() {
	char p[] = "Hello";
	return p;
}
char* GetString2() {
	char* p = "Hello";
	return p;

}

int main() {
	std::cout<<"1"<<GetString1() <<std::endl;
	std::cout<<"2"<<GetString2() <<std::endl;
}
