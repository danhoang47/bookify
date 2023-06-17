import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useContext, useState } from "react";
import { UserContext } from "../contexts";
import FetchUser from "@/services-new/user/FetchUser";
import UpdateUser from "@/services-new/user/UpdateUser";
export default function useFetchUser() {
  const [userData, setUserData] = useState();
  const queryClient = useQueryClient();
  const { user } = useContext(UserContext);
  const _id = user._id;
  const { isLoading, data } = useQuery({
    queryKey: ["fetchUser", _id],
    queryFn: () => FetchUser(_id),
    onSuccess: (data) => {
      console.log(data);
      setUserData(data);
    },
  });
  const { mutate: updateUser } = useMutation({
    mutationKey: ["update user", user._id],
    mutationFn: (userForm) => UpdateUser(userForm),
    onSuccess: async (data, variables) => {
      if (data.status === 401) {
      }
      await queryClient.refetchQueries({ queryKey: ["posts"], type: "active" });
    },
  });
  return { isLoading, updateUser, userData, setUserData };
}
