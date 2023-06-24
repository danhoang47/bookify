import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useContext, useState } from "react";
import { UserContext } from "@/utils/contexts";
import {
  FetchUser,
  GetBookMarked,
  UpdateBankingCard,
  UpdateUser,
  newPassowrdUpdate,
  GetBookingHistory,
} from "@/services-new/user";
import { AddFavorite } from ".";
import AddDeleteBookMarked from "@/services-new/user/AddDeleteBookMarked";
export default function useUser() {
  const [userData, setUserData] = useState();
  const queryClient = useQueryClient();
  const { user } = useContext(UserContext);
  // console.log(user);
  const _id = user._id;
  const { isLoading, data } = useQuery({
    queryKey: ["fetchUser", _id],
    queryFn: () => FetchUser(user._id),
    onSuccess: (data) => {
      // console.log(data);
      setUserData(data);
    },
  });
  const { data: bookMarkedData } = useQuery({
    queryKeyKey: ["get-bookmarked"],
    queryFn: GetBookMarked,
  });
  const { mutate: updateUser } = useMutation({
    mutationKey: ["update user", user._id],
    mutationFn: (userForm) => UpdateUser(userForm),
    onSuccess: async (data, variables) => {
      if (!data) {
      }
      await queryClient.refetchQueries({ queryKey: ["posts"], type: "active" });
    },
  });
  const { mutate: updatePass } = useMutation({
    mutationKey: ["update-password"],
    mutationFn: (pass) => newPassowrdUpdate(pass),
  });
  const { mutate: updateCard } = useMutation({
    mutationKey: ["update-Card_number"],
    mutationFn: (number) => UpdateBankingCard(number),
  });
  const { mutate: addFavorite } = useMutation({
    mutationKey: ["add-bookmarked"],
    mutationKey: (_id) => AddFavorite(_id),
  });
  const { mutate: addBookMarked } = useMutation({
    mutationKey: ["add-bookmarked"],
    mutationFn: (id) => AddDeleteBookMarked(id),
    onSettled: async () => {
      await queryClient.refetchQueries("get-bookmarked");
    },
  });
  const { mutate: getBookingHistory } = useMutation({
    mutationKey: ["booking-history:", _id],
    mutationFn: (filter) => GetBookingHistory(filter),
  });

  return {
    isLoading,
    updateUser,
    userData,
    setUserData,
    updatePass,
    updateCard,
    addFavorite,
    getBookingHistory,
    addBookMarked,
    bookMarkedData,
  };
}
