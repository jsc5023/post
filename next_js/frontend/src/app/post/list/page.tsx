import client from "@/src/lib/backend/client";
import ClinetPage from "./ClientPage";

export default async function Page({
  searchParams,
}: {
  searchParams: {
    keywordType?: "title" | "content";
    keyword: string;
    pageSize: number;
    page: number;
  };
}) {
  const {
    keywordType = "title",
    keyword = "",
    pageSize = 10,
    page = 1,
  } = await searchParams;

  const response = await client.GET("/api/v1/posts", {
    params: {
      query: {
        keyword,
        keywordType,
        pageSize,
        page,
      },
    },
  });

  const rsData = response.data!!;

  return (
    <ClinetPage
      rsData={rsData}
      pageSize={pageSize}
      keyword={keyword}
      keywordType={keywordType}
      page={page}
    />
  );
}
