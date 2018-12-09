#include <iostream>
using namespace std;

class A {
	public:
		virtual void Fun(int a = 10) {
			cout<<"A::Fun number="<<a<<endl;
		}
};
class B: public A {
	public:
		virtual void Fun(int a = 20) {
			cout<<"B::Fun number="<<a<<endl;
		}
};

int main() {
	B b;
	A &a = b;
	a.Fun();
	A *aPoint = &b;
	aPoint->Fun();
}
