#include <iostream>
#include <fstream>
#include <string>
#include <stdio.h>
#include <ctime>
using namespace std;
int custcounter = 1, medcounter = 1, supcounter = 1, temp = 0, counters[3] = {};
char character, character2;

struct Date {
	int Day;
	int Month;
	int Year;
};

struct Customer {
	string IDcust = "U";
	string Name;
	int Phone = 0;
	string purchasedProducts;
	Date dateOfLastBill;
	int its_value = 0;
	int totalPriceOfBoughtProductsPerWeek = 0;
};

Customer custom[100];

struct Supplier {
	string IDsup = "S";
	string Name;
	int Phone = 0;
	string suppliedMedicalProduct;
};

Supplier new_supplier[100];

struct MedicalSupply {
    string IDmed = "M";
    string Name;
    Date expiryDate;
    string SupplierName;
    int Stock = 0;
    int Price = 0;
};

MedicalSupply new_medicalsupply[100];

void add_customer() {
	cout << custom[custcounter - 1].IDcust << custcounter << endl;
	cout << "Enter customer name : ";
	getline (cin, custom[custcounter - 1].Name);
	cout << "Enter phone number : ";
	cin >> custom[custcounter - 1].Phone;
	cin.ignore();
	cout << "Enter purchase products : ";
	getline (cin, custom[custcounter - 1].purchasedProducts);
	cout << "Enter date of last bill : ";
	cin >> custom[custcounter - 1].dateOfLastBill.Day >> custom[custcounter - 1].dateOfLastBill.Month >> custom[custcounter - 1].dateOfLastBill.Year;
    cout << "Enter its value : ";
	cin >> custom[custcounter - 1].its_value;
	cout << "Enter total price of bought products/week : ";
	cin >> custom[custcounter - 1].totalPriceOfBoughtProductsPerWeek;
	custcounter++;
}

void add_supplier() {
    cout << new_supplier[supcounter - 1].IDsup << endl;
    cout << "Enter supplier name : ";
    getline(cin, new_supplier[supcounter - 1].Name);
    cout << "Enter phone number : ";
    cin >> new_supplier[supcounter - 1].Phone;
    cin.ignore();
    cout << "Enter supplied products : ";
    getline(cin, new_supplier[supcounter - 1].suppliedMedicalProduct);
    cout << endl;
    supcounter++;
}

void add_medicalsupply() {
    cout << new_medicalsupply[medcounter - 1].IDmed << medcounter << endl;
    cout << "Enter medical supply name : ";
    getline(cin, new_medicalsupply[medcounter - 1].Name);
    cout << "Enter date of expiry : ";
    cin >> new_medicalsupply[medcounter - 1].expiryDate.Day >> new_medicalsupply[medcounter - 1].expiryDate.Month >> new_medicalsupply[medcounter - 1].expiryDate.Year;
    cin.ignore();
    cout << "Enter name of supplier: ";
    getline(cin, new_medicalsupply[medcounter - 1].SupplierName);
    cout << "Enter Stock number: ";
    cin >> new_medicalsupply[medcounter-1].Stock;
    cout << "Enter price: ";
    cin >> new_medicalsupply[medcounter-1].Price;
    cout << endl;
    medcounter++;
}

void read_customer_file(Customer customer[]) {
    int count = 0, array_count = 0;
        ifstream fs;
        string line;
        fs.open("customer.txt");
        
        while (getline(fs, line))
        {
            if (count == 0)
            {
                customer[array_count].IDcust = line;
            }
            else if (count == 1) {
                customer[array_count].Name = line;
            }
            else if (count == 2) {
                customer[array_count].Phone = std::stoi(line);
            }
            else if (count == 3) {
                customer[array_count].purchasedProducts = line;
            }
            else if (count == 4) {
                customer[array_count].dateOfLastBill.Day = std::stoi(line);
            }
            else if (count == 5) {
                customer[array_count].dateOfLastBill.Month = std::stoi(line);
            }
            else if (count == 6) {
                customer[array_count].dateOfLastBill.Year = std::stoi(line);
            }
            else if (count == 7) {
                customer[array_count].its_value = std::stoi(line);
            }
            else {
                customer[array_count].totalPriceOfBoughtProductsPerWeek = std::stoi(line);
            }
                count++;

                if (count > 8)
                {
                    count = 0;
                    array_count++;
                }
               
        }
        fs.close();
        
}

void update_customer() {
    cout << "Enter phone number: ";
    int temp,temp2 = 0; bool flag = false;
    cin >> temp;
    for (int i = 0; i < 100; i++) {
        if (temp == custom[i].Phone) {
            flag = true;
            temp2 = i;
            break;
        }
    }
    if (flag == false) {
        cout << "This customer is not found!" << endl;
    }
    else {
        cout << "Customer name: " << custom[temp2].Name << endl;
        cout << "Enter date of last bill: ";
        cin >> custom[temp2].dateOfLastBill.Day >> custom[temp2].dateOfLastBill.Month >> custom[temp2].dateOfLastBill.Year;
        cout << "Enter its value: ";
        cin >> custom[temp2].its_value;
        custom[temp2].totalPriceOfBoughtProductsPerWeek += custom[temp2].its_value;
        cout << "Do you want to continue?";
    }
}

void write_customer_file() {
    ofstream cf;
    cf.open("customer.txt");
    for (int i = 0; i < custcounter-1; i++) {
        cf << custom[i].IDcust << custcounter-1 << endl;
        cf << custom[i].Name << endl;
        cf << custom[i].Phone << endl;
        cf << custom[i].purchasedProducts << endl;
        cf << custom[i].dateOfLastBill.Day << endl << custom[i].dateOfLastBill.Month << endl << custom[i].dateOfLastBill.Year << endl;
        cf << custom[i].its_value << endl;
        cf << custom[i].totalPriceOfBoughtProductsPerWeek << endl;
    }
    cf.close();
}
void read_Medicalsupply_file(MedicalSupply medicalsupply[]) {
    int count = 0, array_count = 0;
    ifstream ms;
    string line;
    ms.open("Medicalsupply.txt");
    while (getline(ms, line)) {
        if (count == 0) {
            medicalsupply[array_count].IDmed = line;
        }
        else if (count == 1) {
            medicalsupply[array_count].Name = line;
        }
        else if (count == 2) {
            medicalsupply[array_count].expiryDate.Day = std::stoi(line);
        }
        else if (count == 3) {
            medicalsupply[array_count].expiryDate.Month = std::stoi(line);
        }
        else if (count == 4) {
            medicalsupply[array_count].expiryDate.Year = std::stoi(line);
        }
        else if(count==5){
            medicalsupply[array_count].SupplierName = line;
        }
        else if (count==6) {
            medicalsupply[array_count].Stock = std::stoi(line);
        }
        else {
            medicalsupply[array_count].Price = std::stoi(line);
        }
        count++;
        if (count > 7) {
            count = 0;
            array_count++;
        }
    }
    ms.close();
}
void write_Medicalsupply_file() {
    ofstream mf;
    mf.open("Medicalsupply.txt");
    for (int i = 0; i < medcounter - 1; i++) {
        mf << new_medicalsupply[i].IDmed << medcounter - 1 << endl;
        mf << new_medicalsupply[i].Name << endl;
        mf << new_medicalsupply[i].expiryDate.Day << endl << new_medicalsupply[i].expiryDate.Month << endl << new_medicalsupply[i].expiryDate.Year << endl;
        mf << new_medicalsupply[i].SupplierName << endl;
        mf << new_medicalsupply[i].Stock << endl;
        mf << new_medicalsupply[i].Price << endl;
    }
    mf.close();
}
void read_supplier_file(Supplier supplier[])
{
    int count = 0, array_count = 0;
    ifstream fs;
    string line;
    fs.open("supplier.txt");
    while (getline(fs, line))
    {
        if (count == 0)
        {
            supplier[array_count].IDsup = line;
        }
        else if (count == 1)
        {
            supplier[array_count].Name = line;
        }
        else if (count == 2)
        {
            supplier[array_count].Phone = std::stoi(line);
        }
        else if (count == 3)
        {
            supplier[array_count].suppliedMedicalProduct = line;
        }
        count++;
        if (count > 3)
        {
            count = 0;
            array_count++;
        }
    }
    fs.close();
}
void update_supplier()
{
    cout << "Enter Name of Supplier:";
    string temp1, temp3;
    int temp2 = 0; bool flag = false;
    cin >> temp1;
    for (int i = 0; i < 100; i++)
    {
        if (temp1 == new_supplier[i].Name)
        {
            flag = true;
            temp2 = i;
            break;
        }
    }
    if (flag == false)
    {
        cout << "This supplier is not found!" << endl;
    }
    else
    {
        cout << "supplier phone:" << new_supplier[temp2].Phone << endl;
        cout << "supplied medical product:";
        cin >> temp3;
        new_supplier[temp2].suppliedMedicalProduct += " ";
        new_supplier[temp2].suppliedMedicalProduct += temp3;
        cout << "Do you want to continue?";
    }
}
void write_supplier_file()
{
    ofstream cf;
    cf.open("supplier.txt");
    for (int i = 0; i < supcounter - 1; i++)
    {
        cf << new_supplier[i].IDsup << supcounter - 1 << endl;
        cf << new_supplier[i].Name << endl;
        cf << new_supplier[i].Phone << endl;
        cf << new_supplier[i].suppliedMedicalProduct << endl;
    }
    cf.close();
}
void read_counters() {
    ifstream cf;
    cf.open("counters.txt");
    cf >> custcounter >> medcounter >> supcounter;
    cf.close();
}
void write_counters() {
    ofstream cf;
    cf.open("counters.txt");
    cf << custcounter << endl << medcounter << endl << supcounter;
    cf.close();
}

void mainmenu() {
    cout << "\t\t\t\t\tWelcome\n\n";
    cout << "\t\t\t\t  Bio*17 pharma store\n";

    cout << "\t\t\t*********************************************\n";

    cout << "\n\tFor customers menu press C ";
    cout << "\tFor products menu press M";
    cout << "\tFor suppliers menu press S\n\n";

    cout << "\tENTER YOUR CHOICE: ";
    cin.get(character);
    cin.ignore();
    if (character == 'c' || character == 'C') {
        cout << "\n Customer menu:-\n";
        cout << "\tFor new customer press N \n";
        cout << "\tFor current customer press U\n\n";
        cout << "\tENTER YOUR CHOICE: ";
        cin.get(character2);
        cin.ignore();
        if (character2 == 'n' || character2 == 'N') {
            add_customer();
        }
        else if (character2 == 'U' || character2 == 'u') {
            update_customer();
        }
    }


    else if (character == 'M' || character == 'm') {
        cout << "\n Medical supply menu:-\n";
        cout << "\tFor new product press W\n";
        cout << "\tFor current product press E\n";
        cout << "\tENTER YOUR CHOICE: ";
        cin.get(character2);
        cin.ignore();
        if (character2 == 'w' || character2 == 'W') {
            add_medicalsupply();
        }
        else if (character2 == 'e' || character2 == 'E') {
            cout << "\tupdated\n";
        }
    }

    else if (character == 's' || character == 'S') {
        cout << "\n Supplier menu:-\n";
        cout << "\tFor new supplier press R\n";
        cout << "\tFor current supplier press T\n";
        cout << "\tENTER YOUR CHOICE: ";
        cin.get(character2);
        cin.ignore();
        if (character2 == 'r' || character2 == 'R') {
            add_supplier();
        }
        else if (character2 == 'T' || character2 == 't') {
            update_supplier();
        }
    }


    else {
        cout << "INVALIDE CHOICE,PLEASE TRY AGAIN\n";
    }
}

int main() {
    read_counters();
    read_customer_file(custom);
    read_Medicalsupply_file(new_medicalsupply);
    read_supplier_file(new_supplier);
    mainmenu();
    write_customer_file();
    write_Medicalsupply_file();
    write_supplier_file();
    write_counters();
    return 0;
}
