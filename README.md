# earthquake_collector

- Deprem verilerini verilen aralıkta http://udim.koeri.boun.edu.tr/zeqmap/xmlt/son24saat.xml adresinden çekerek yeni bir deprem varsa bunu **Kafka**'da bir topic'e(**earthquake**) yazar.
- Kafka'ya yazılan bu topicleri dinleyen **Flink** uygulaması ise gerekli analizleri yaparak bunu bir topic'e(**analyzedTopic**)  yazar.

- Kullanıcıdan(Mobile App) gelen ayarları ve analyzedTopic'i dinleyen **Push Service** ise  bunu Expo Server'a iletecek olan **Node.js** uygulamasına iletir.

- Node.js uygulaması Mobile app'den gelen ExponentPushToken, Location ve  TimeInterval değelerini bildiği için gelen istediği **Expo**'ya iletir.

- Expo ise gelen isteğin hangi cihaza gideceğini bildiği için oraya push gönderir.

> Flink vb olaylarına girmeden sadece root dizindeki **docker-compose up**
yaparak **earthquake** topic'ini dinlerseniz son depremlere ulaşabilirsiniz.

> Önemli: Proje canlı bir ortamda çalışmadığı için ngrok ile yönlendirme yaptım **Zuul**'a onun için  **app/service/userConfigService.js** ve  **app/push/registerPushNotification.js** dosyalarında **http://*********.ngrok.io** şeklinde gördüğünüz yerleri local'de **ngrok** başlatıp **8762** portunu açarsanız çalışmaya başlar.

---

ngrok kullanımı ->

```bash
 ./ngrok http 8762
 ```
---
Tüm proke aşağıdaki sıra ile çalıştırabilir(Hepsi dockerize olduğu için) :

```bash
docker-compose up --build

cd pushnotificationhandler
docker-compose up --build

cd app
expo start

```

## Endpoints

> Eureka : localhost:8761

> Zuul: localhost:8762

> Expo-server: localhost:3000

Services: 

- user-configs(User'dan gelen location ve time bilgisini kafkaya yazar.)
- token-service(User'dan gelen Exponenttoken bilgisini kafkaya yazar.)
- db-service(Kafkaya yazılan servisten gelen bilgileri db'ye yazar.)



## Kafka & Scraper & Steaming Engine & Expo Server

**Start** :

```bash
docker-compose up --build
```

**Stop**:

```bash
docker-compose down -v --rmi all --remove-orphans
```

---

### **Kafka** 

Yardımcı olması için aşağıdakş komutlarla kafkaya yazılan eventleri görebilirsiniz.

**Bash**:

```bash
docker exec -it kafka_cayan  /bin/sh
```

**EartQuake Comsumer**:
```bash
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic earthquake --from-beginning
```

**EartQuake Producer**:

```bash
kafka-console-producer.sh --broker-list localhost:9092 --topic earthquake
```

**AnalyzedTopic Producer**:

```bash
kafka-console-producer.sh --broker-list localhost:9092 --topic analyzedTopic
```

---

## Push Notification Handler
**Start** :

```bash
cd pushnotificationhandler
docker-compose up --build
```

**Stop**:

```bash
docker-compose down -v --rmi all --remove-orphans
```

## Mobile App

![/images/mobile_app.jpeg](/images/mobile_app.jpeg)
Görsel-1

![/images/mobile_app2.jpeg](/images/mobile_app2.jpeg)
Görsel-2
 
```bash
cd app
expo start
```