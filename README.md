# Athena

Athena的回复分析基于ElasticSearch，我需要有大量的聊天数据，除了我手机中现存的，我更希望可以拿到格式与依赖更加完整了聊天消息。

所以我在Athena中引入IM通讯模型，基于WebSocket，目前没有考虑IM中引入路由和服务发现等模块，只是一个简单的通讯模块。

在发送消息时，如果这条消息不依赖于上文，是一条新话题，你就需要选中"新消息"，并且在id栏中指定这个话题的SaidId。

如果不是新消息，就需要选中"回复"，在id栏中指明是回复哪一个话题的SaidId。

![](https://raw.githubusercontent.com/CasterWx/gitpics/master/img/20191217180315.png)
![](https://raw.githubusercontent.com/CasterWx/gitpics/master/img/20191217180342.png)
支持用户上下线提醒。