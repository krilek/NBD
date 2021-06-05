// 2. Łączną ilość środków pozostałych na kartach kredytowych osób w bazie, w podziale na waluty
db.people.mapReduce(
  function () {
    this.credit.forEach(cc => {
      emit(cc.currency, Number(cc.balance));
    });
  },
  function (key, values) {
    return Array.sum(values);
  },
  { out: 'currency_sums' }
);

printjson(db.currency_sums.find().toArray());