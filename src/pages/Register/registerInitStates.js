const basicHotelInforInitState = {
  name: "",
  type: "",
  country: "Việt Nam",
  province: "",
  district: "",
  address: "",
  description: "",
};

const roomInfoInitState = {
  bedrooms: 1,
  guests: 1,
  beds: 6,
  bathrooms: 1,
  price: 100,
  isPrivateBathRoom: false,
};

const extraInforInitState = {
  isHasCamera: false,
  isAllowPet: false,
  checkin: {
    hour: 12,
    minutes: 30,
  },
  checkout: {
    hour: 12,
    minutes: 30,
  },
  opening: {
    hour: 12,
    minutes: 30,
  },
  closing: {
    hour: 12,
    minutes: 30,
  },
};

export { basicHotelInforInitState, roomInfoInitState, extraInforInitState };
