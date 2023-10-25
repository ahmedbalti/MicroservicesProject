const mongoose = require("mongoose");

const AccomodationSchema = mongoose.Schema(
  {

    idAcc: {
      type: Number, // or any other data type you want for the idAcc field
      required: true,
      unique: true, // Ensure it's unique, if needed
    },
    title: {
      type: String,
      required: [true, "Please enter a Accomodation name"],
    },
    description: {
      type: String,
      required: [true, "Please enter a Accomodation description"],
    },
    size: {
      type: Number,
      required: true,
      default: 0,
    },
    roomNumber: {
      type: Number,
      required: true,
    },
    roomNumber: {
      type: Number,
      required: true,
    },
    bathroomNumber: {
      type: String,
      required: false,
    },
    country: {
      type: String,
      required: false,
    },
    city: {
      type: String,
      required: false,
    },
    location: {
      type: String,
      required: false,
    },
    rules: {
      type: String,
      required: false,
    },
    price: {
      type: Number,
      required: false,
    },
    bail: {
      type: Number,
      required: false,
    },
    likes: {
      type: Boolean,
      required: false,
    },
    furniture: {
      type: Boolean,
      required: false,
    },
  },
  {
    timestamps: true,
  }
);

const Accomodation = mongoose.model("Accomodation", AccomodationSchema);
module.exports = Accomodation;
