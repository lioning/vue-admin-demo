import Mock from 'mockjs';
const LoginUsers = [
  {
    id: 1,
    username: 'admin',
    password: '123456',
    avatar: 'https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png',
    name: '张某某'
  }
];

const Users = [];

for (let i = 0; i < 86; i++) {
  Users.push(Mock.mock({
    id: Mock.Random.guid(),
    name: Mock.Random.cname(),
    addr: Mock.mock('@county(true)'),
    'age|18-60': 1,
    birth: Mock.Random.date(),
    sex: Mock.Random.integer(0, 1)
  }));
}
/*
const Stores = [{"idxx": "",
                    "name": "周平",
                    "addr":"大洞口区",
                    "no":"C001",
                    "birth":"2000-01-01",
                    "sex":"1"},
                {"idxx": "",
                    "name": "方青山",
                    "addr":"天上人间",
                    "no":"C002",
                    "birth":"2000-01-01",
                    "sex":"0"}]
*/

const Stores = [];

for (let i = 0; i < 5; i++) {
  Stores.push(Mock.mock({
    no: Mock.Random.id(),
    name: Mock.Random.csentence(),
    addr: Mock.mock('@county(true)')
  }));
}


export { LoginUsers, Users, Stores };
//export { LoginUsers, Users };
