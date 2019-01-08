from faker import Faker
import random
# 企业电话
"-".join( map(lambda r:str(r), [random.randint(100,999),random.randint(100,999),random.randint(100,999)]) )
# fake=Faker() #默认生成美国英文数据
fake=Faker(locale='zh_CN')

def supplier_data():
    for i in range(1,101):
        no = "S" + '%05d' % i
        name = fake.company()
        addr = fake.address()
        phone = "-".join( map(lambda r:str(r), [random.randint(100,999),random.randint(100,999),random.randint(100,999)]) )
        print (no, ",", name, ",", addr, ",", phone)

def product():
        import itertools
        no = 1
        for p1, p2 in itertools.product(['中国','法国','英格兰','旦西堡','葡萄牙','美国','奥尔良'], ['面包','馒头','红酒','羊毛衫','外套','鸭脖']):
            name = p1+p2  
            price = random.randint(10,1000)
            price_vip = price-0.01
            serial = fake.isbn10(separator="-")
            discount = '%.2f' % random.random()
            unit = random.choice(['罐','箱','瓶','件'])
            supplier = "S" + '%05d' % random.randint(1,101)

            print ('P' + '%05d' % no + "," + str(name) + "," + str(price) + "," + str(price_vip) + "," + str(serial) + "," + str(discount) + "," + str(unit) + "," + str(supplier))
            no += 1
# (`product_no`,
# `product_name`,
# `product_price`,
# `product_price_vip`,
# `product_serial`,
# `product_discount`,
# `product_unit`,
# `product_supplier_no`)


def user_data():
    #个人信息类
    for _ in range(20):
        name = fake.user_name()
        addr = fake.address()
        phone = fake.phone_number()
        pwd = fake.password(special_chars=False)
        print (name, ",", 1, ",", pwd, ",", addr, ",", phone)

def store_data():
    for i in range(1, 25):
        no = "D" + '%04d' % i
        addr = fake.street_address() 
        name = addr + "分店"
        phone = "(" + str(random.randint(100,999)) + ")" + str(random.randint(100,1000)).zfill(3) + str(random.randint(1000,100000)).zfill(5)
        print (no, ",", name, ",", addr, ",", phone )
def amount_data():
    for i in range(1, 25):
        no = "D" + '%04d' % i
        for yy in range(2010, 2019):
                for mm in range(1, 13):
                        print (no + "," + str(yy) + "," + str(mm).zfill(2) + "," + str(random.randint(10,100)))




# user_data()
# supplier_data()
# store_data()
amount_data()
# product()