import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useContext, useState } from "react";
import { UserContext } from "@/utils/contexts";
import {
  FetchUser,
  UpdateBankingCard,
  UpdateUser,
  newPassowrdUpdate,
} from "@/services-new/user";
import { AddFavorite } from ".";
export default function useUser() {
  const [userData, setUserData] = useState(); 
  const queryClient = useQueryClient();
  const { user } = useContext(UserContext);
  console.log(user);
  const _id = user._id;
  const { isLoading, data } = useQuery({
    queryKey: ["fetchUser", _id],
    queryFn: () => FetchUser(user._id),
    onSuccess: (data) => {
      console.log(data);
      setUserData(data);
    },
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
  const {mutate:addBookmarked}= useMutation({
    mutationKey:["add-bookmarked"],
    mutationKey:(_id)=>AddFavorite(_id)
  })

  return {
    isLoading,
    updateUser,
    userData,
    setUserData,
    updatePass,
    updateCard,
  };
}
