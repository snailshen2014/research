#include <iostream>
class A {
	public:
	A() {
		std::cout<<"A is created"<<std::endl;
		print();
	}
	~A() {
		std::cout <<"A is deleted."<<std::endl;
	}
	virtual void print() {
		std::cout<<"A:print() called ."<<std::endl;
	}
};
class B :public A {
	public:
	B() {
		std::cout<<"B is created"<<std::endl;
		print();
	}
	~B() {
		std::cout <<"B is deleted."<<std::endl;
	}
	virtual void print() {
		std::cout<<"B:print() called ."<<std::endl;
	}
};
int main (int argc,char* argv[]) {
	A* pA = new B();
	delete pA;
	return 0;
}

