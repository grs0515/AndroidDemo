//书籍列表
var bookData = {
  "books": [{
      "author": "陈儒",
      "fav_nums": 0,
      "id": 18,
      "image": "https://img3.doubanio.com/lpic/s3435132.jpg",
      "like_status": 0,
      "title": "Python源码剖析",
      "comment": 1
    },
    {
      "author": "MarkPilgrim",
      "fav_nums": 10,
      "id": 58,
      "image": "https://img3.doubanio.com/lpic/s29631790.jpg",
      "like_status": 0,
      "title": "Dive Into Python",
      "comment": 4
    },
    {
      "author": "Luciano Ramalho",
      "id": 195,
      "fav_nums": 7,
      "image": "https://img3.doubanio.com/lpic/s27935775.jpg",
      "like_status": 1,
      "comment": 22,
      "title": "Fluent Python"
    },
    {
      "author": "【英】大卫•加里夫",
      "id": 44307,
      "fav_nums": 2,
      "image": "https://img3.doubanio.com/lpic/s27145681.jpg",
      "like_status": 1,
      "comment": 222,
      "price": "98.00元",
      "title": "艺术谱系"
    }, {
      "author": "陈儒",
      "fav_nums": 0,
      "id": 18,
      "image": "https://img3.doubanio.com/lpic/s3435132.jpg",
      "like_status": 0,
      "title": "Python源码剖析",
      "comment": 1
    },
    {
      "author": "MarkPilgrim",
      "fav_nums": 10,
      "id": 58,
      "image": "https://img3.doubanio.com/lpic/s29631790.jpg",
      "like_status": 0,
      "title": "Dive Into Python",
      "comment": 4
    },
    {
      "author": "Luciano Ramalho",
      "id": 195,
      "fav_nums": 7,
      "image": "https://img3.doubanio.com/lpic/s27935775.jpg",
      "like_status": 1,
      "comment": 22,
      "title": "Fluent Python"
    },
    {
      "author": "【英】大卫•加里夫",
      "id": 44307,
      "fav_nums": 2,
      "image": "https://img3.doubanio.com/lpic/s27145681.jpg",
      "like_status": 1,
      "comment": 222,
      "price": "98.00元",
      "title": "艺术谱系"
    }
  ],
  "count": 8,
  "start": 0,
  "total": 20
}
//书籍详情
var bookDetail = {
  "author": [
    "Wolfgang Mauerer"
  ],
  "binding": "平装",
  "category": "算法",
  "id": 6980,
  "image": "https://img1.doubanio.com/lpic/s4368169.jpg",
  "images": {
    "large": "https://img1.doubanio.com/lpic/s4368169.jpg"
  },
  "isbn": "9787115227430",
  "pages": "1038",
  "price": "149.00元",
  "pubdate": "2010-05",
  "publisher": "人民邮电出版社",
  "subtitle": "全球开源社区集体智慧结晶，领略Linux内核的绝美风光",
  "summary": "众所周知，Linux操作系统的源代码复杂、文档少，对程序员的要求高，要想看懂这些代码并不是一件容易事... \nLinux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。",
  "title": "深入Linux内核架构",
  "translator": [
    "郭旭"
  ]
}
//短评
var bookComments = {
  "comment": [{
      "content": "i hate6!",
      "nums": 1
    },
    {
      "content": "hhhhh!",
      "nums": 4
    },
    {
      "content": "不错吆~",
      "nums": 33
    },
    {
      "content": "good",
      "nums": 2
    },
    {
      "content": "i hate6!",
      "nums": 51
    },
    {
      "content": "喜欢",
      "nums": 9
    },
    {
      "content": "😍😍",
      "nums": 116
    }
  ],
  "book_id": 1
}


module.exports = {
  bookData: bookData,
  bookDetail: bookDetail,
  bookComments: bookComments,
}