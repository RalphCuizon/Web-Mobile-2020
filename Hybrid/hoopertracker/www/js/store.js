

var createStore = Framework7.createStore;
const store = createStore({
  state: {
    userinfo: [
      {
        id: '1',
        email: 'test'
      },
    ]
  },
  getters: {
    userinfo({ state }) {
      return state.userinfo;
    }
  },
  actions: {
    addProduct({ state }, product) {
      state.products = [...state.products, product];
    },
    updateUserInfo({ state}) {
      state.userinfo.push(...userinfo);
    }
  },
})

