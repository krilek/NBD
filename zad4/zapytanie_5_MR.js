// 5. Średnia i łączna ilość środków na kartach kredytowych kobiet narodowości polskiej w podziale na waluty
db.people.mapReduce(
  function () {
    if (this.nationality === "Poland" && this.sex == "Female") {
      this.credit.forEach(cc => emit(cc.currency, { sumBalance: Number(cc.balance), count: 1 }))
    }
  },
  function (key, values) {
    return { sumBalance: Array.sum(values.map(c => c.sumBalance)), count: values.length }
  },
  {
    finalize: function(key, value) {
      return {sumBalance: value.sumBalance, averageBalance: (value.sumBalance / value.count) }
    },
    out: 'balances'
  }
);
printjson(db.balances.find().toArray());
